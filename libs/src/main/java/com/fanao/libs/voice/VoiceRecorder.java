package com.fanao.libs.voice;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.fanao.libs.utils.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 录音
 * 
 * @author liutao
 */
public class VoiceRecorder {
	public static final int RECORDING = 1;
	public static final int RECORDING_END = 2;

	private Context context = null;
	private Handler handler = null;
	private MediaRecorder recorder;
	private boolean isRecording = false;
	private long startTime = 0;
	private String voiceFilePath = null;
	private String voiceFileName = null;
	private File file = null;
	private ExecutorService es = null;
	
	public VoiceRecorder(Context context, Handler paramHandler) {
		this.context = context;
	    this.handler = paramHandler == null ? new Handler() : paramHandler;
	    
	    // 主要为了2.X系统出现360提示权限允许对话框时，导致应用未响应出现的闪退
	    es = Executors.newCachedThreadPool();
	    es.execute(run);
	}
	
	private String setVoiceFileName(String voiceName) {
		String name = null;
		if(TextUtils.isEmpty(voiceName)) {
			name = DateUtils.getUserDate("yyyyMMddhhmm").concat(".amr");
		}else {
			name = voiceName.concat(".amr");
		}
		return name;
	}
	
	private Runnable run = new Runnable() {
		@Override
		public void run() {
			try {
				recorder = new MediaRecorder();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	/**
	 * 开始录音
	 * 
	 * @param voiceName
	 * @return
	 */
	public String startRecording(String voiceName) {
		file = null;
	    try{
	    	if (recorder != null) {
	    		recorder.release();
	    		recorder = null;
	    	}
	    	recorder = new MediaRecorder();
	    	recorder.setAudioSource(1);
	    	recorder.setOutputFormat(3);
	    	recorder.setAudioEncoder(1);
	    	recorder.setAudioChannels(1);
	    	recorder.setAudioSamplingRate(8000);
	    	recorder.setAudioEncodingBitRate(64);
	    	
	    	voiceFileName = setVoiceFileName(voiceName);
	    	voiceFilePath = getVoiceFilePath();
	    	file = new File(voiceFilePath);
	    	
	    	recorder.setOutputFile(file.getAbsolutePath());
	    	recorder.prepare();
	    	
	    	isRecording = true;
	    	
	    	recorder.start();
	    } catch (IOException localIOException) {
	    	Log.e("voice", "prepare() failed");
	    }
	    
	    new Thread(new Runnable() {
	    	public void run() {
	    		try {
	    			while (isRecording) {
	    				Message localMessage = new Message();
						localMessage.what = RECORDING;
	    				localMessage.arg1 = (recorder.getMaxAmplitude() * 13 / 32767);
	    				localMessage.arg1 = localMessage.arg1 < 0 ? 0 : localMessage.arg1;
	    				handler.sendMessage(localMessage);
	    				SystemClock.sleep(100L);
	    			}
	    		} catch (Exception localException) {
	    			Log.e("voice", localException.toString());
	    		}
	    	}
	    }).start();
	    
	    startTime = new Date().getTime();
	    
	    return file == null ? null : file.getAbsolutePath();
	}
	
	/**
	 * 销毁录音
	 */
	public boolean discardRecording() {
	    if (recorder != null) {
	    	try {
	    		recorder.stop();
	    		recorder.release();
	    		recorder = null;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	isRecording = false;
	    }
	    
	    if (file != null && file.exists() && !file.isDirectory()) {
	    	file.delete();
	    	voiceFileName = null;
			return true;
	    }

		return false;
	}
	
	/**
	 * 结束录音
	 * 
	 * @return 返回录音时长，单位为秒
	 */
	public int stopRecoding() {
		try {
			handler.sendEmptyMessage(RECORDING_END);

			if (recorder != null) {
				isRecording = false;
				recorder.stop();
				recorder.release();
				recorder = null;
				
				if (file == null || !file.exists() || !file.isFile())
					return -1;
				
				if (file.length() == 0L) {
					file.delete();
					return -1;
				}
				
				int i = (int)(new Date().getTime() - startTime) / 1000;
				
				return i;
			}
		} catch (Exception e) {}
	    
	    return -1;
	}
	
	/**
	 * 是否正在录音
	 * 
	 * @return true为正在录音，false录音结束
	 */
	public boolean isRecording() {
		return isRecording;
	}
	
	/**
	 * 返回录音文件名字
	 * 
	 * @return
	 */
	public String getVoiceFileName() {
		return voiceFileName;
	}
	
	/**
	 * 返回录音文件地址
	 * 
	 * @return
	 */
	public String getVoiceFilePath() {
		return context.getExternalFilesDir(android.os.Environment.DIRECTORY_MUSIC) + File.separator + voiceFileName;
	}
}