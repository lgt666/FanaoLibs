package com.fanao.libs.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.MeasureSpec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 图像工具
 * 
 * @author liutao
 */
public class ImageUtlis {
	
	/**
	 * 压缩图片
	 * 
	 * @param imgPath      图片保存路径
	 * @param compImgPaht  压缩后图片保存路径
	 * @param size         压后的图片大小范围KB
	 * @return true 为压缩并保存成功，false为失败。
	 */
	public static boolean compressToFile(File imgPath, File compImgPaht, int size) {
		boolean isNormal = true;
		
		if(imgPath == null || compImgPaht == null)
			return false;
		
		Options options = new Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPurgeable = true;
		options.inSampleSize = 4;
		options.inInputShareable = true;

		FileInputStream fis = null;
		FileOutputStream fos = null;
		ByteArrayOutputStream baos = null;
		Bitmap bitmap = null;
		int quality = 100;  // 压缩比0-100;
		try {
			fis = new FileInputStream(imgPath);

			if((fis.available()/1024) > size) {
				bitmap = BitmapFactory.decodeStream(fis, null, options);

			}else {
				bitmap = BitmapFactory.decodeStream(fis);
			}

			if(bitmap == null)
				return false;

			baos = new ByteArrayOutputStream();

			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

			while((baos.toByteArray().length/1024) > size) {
				baos.reset();

				quality -= 10;

				bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
			}

			fos = new FileOutputStream(compImgPaht);
			fos.write(baos.toByteArray());
            fos.flush();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
            	options = null;

            	if(fis != null) {
            		fis.close();
            		fis = null;
            	}
            	if(fos != null) {
            		fos.close();
            		fos = null;
            	}
				if(baos != null) {
					baos.close();
					baos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return isNormal;
	}

	/**
	 * 获取相册照片
	 *
	 * @param context
	 * @param uri
	 * @return path  相片的绝对路径
	 */
	public static String getAlbumPhotos(Context context, Uri uri) {
		String path = null;
		String[] projection = new String[]{Media._ID, Media.DATA};
		String id = null;
		Cursor cursor = null;
		try {
			if(uri != null) {
				String provider = "com.android.providers.media.documents";
				String download = "com.android.providers.downloads.documents";
				String external = "com.android.externalstorage.documents";
				String media = "media";
				String authContent = "content";
				String authFile = "file";

				String authority = uri.getAuthority();
				String scheme = uri.getScheme();

				if(scheme.equals(authContent)) {
					if(authority.equals(provider)) {
						id =  uri.getPath().substring(uri.getPath().indexOf(":")+1);

						cursor = Media.query(context.getContentResolver(), Media.EXTERNAL_CONTENT_URI,
								projection, Media._ID+"=?", new String[]{id}, null);

					}else if(authority.equals(media)) {
						cursor = Media.query(context.getContentResolver(), uri, projection);

					}else if(authority.equals(external)) {
						String[] p = uri.getPath().split(":");

						path = Environment.getExternalStorageDirectory().toString() + File.separatorChar + p[1];

						return path;

					}else if(authority.equals(download)) {
						id = (uri.getPath().split("/"))[2];

						Uri imgUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
								Long.valueOf(id));

						cursor = context.getContentResolver().query(imgUri, projection, Media._ID+"=?",
								new String[]{id}, null);
					}

					cursor.moveToFirst();

					path = cursor.getString(cursor.getColumnIndex(Media.DATA));

				}else if(scheme.equals(authFile)) {
					path = uri.getPath();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null) {
				cursor.close();
				cursor = null;
			}
		}

		return path;
	}

	/**
	 * 获取图片的输入流
	 *
	 * @param context
	 * @param object 图片的存放路径，Uri、File、String三种类型
	 * @return
	 */
	public static InputStream getPhotosInputStream(Context context, Object object) {
		InputStream is = null;
		try {
			if(object instanceof Uri) {
				is = context.getContentResolver().openInputStream((Uri) object);

			}else if(object instanceof File) {
				is = new FileInputStream((File) object);

			}else if(object instanceof String) {
				is = new FileInputStream(new File(object.toString()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return is;
	}

	/**
	 * Drawable 转成 Bitmap
	 *
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * Bitmap 转成 byte[]
	 *
	 * @param bm
	 * @return
	 */
	public static byte[] BitmapToBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * Byte[] 转成 Bitmap
	 *
	 * @param b
	 * @return
	 */
	public static Bitmap BytesToBimap(byte[] b) {
		if (b.length > 0)
			return BitmapFactory.decodeByteArray(b, 0, b.length);

		return null;
	}

	/**
	 * View 转成 Bitmap
	 *
	 * @param v
	 * @return
	 */
	public static Bitmap viewToBitmap(View v) {
		v.destroyDrawingCache();
		v.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
		v.setDrawingCacheEnabled(true);

		return v.getDrawingCache(true);
	}

	/**
	 * 图片裁剪
	 *
	 * @param object
	 * @param uri  数据源图
	 * @param saveUri 裁剪后保存地址
	 */
	public static void clipImage(Object object, Uri uri, int width, int height, Uri saveUri, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 2);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra("noFaceDetection", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, saveUri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

		if(object instanceof Activity) {
			((Activity) object).startActivityForResult(intent, requestCode);

		}else if(object instanceof Fragment) {
			((Fragment) object).startActivityForResult(intent, requestCode);
		}
	}

	/**
	 * 设置缩放大小图片
	 *
	 * @param dst
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap getBitmapFromFile(File dst, int width, int height) {
	    if (null != dst && dst.exists()) {
	        Options opts = null;
	        if (width > 0 && height > 0) {
	            opts = new Options();
	            opts.inJustDecodeBounds = true;
	            BitmapFactory.decodeFile(dst.getPath(), opts);
	            // 计算图片缩放比例
	            final int minSideLength = Math.min(width, height);
	            opts.inSampleSize = computeSampleSize(opts, minSideLength,
	                    width * height);
	            opts.inJustDecodeBounds = false;
	            opts.inInputShareable = true;
	            opts.inPurgeable = true;
	        }
	        try {
	            return BitmapFactory.decodeFile(dst.getPath(), opts);
	        } catch (OutOfMemoryError e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}
	private static int computeSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
	    int initialSize = computeInitialSampleSize(options, minSideLength,
	            maxNumOfPixels);

	    int roundedSize;
	    if (initialSize <= 8) {
	        roundedSize = 1;
	        while (roundedSize < initialSize) {
	            roundedSize <<= 1;
	        }
	    } else {
	        roundedSize = (initialSize + 7) / 8 * 8;
	    }

	    return roundedSize;
	}
	private static int computeInitialSampleSize(Options options,
	        int minSideLength, int maxNumOfPixels) {
	    double w = options.outWidth;
	    double h = options.outHeight;

	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
	            .sqrt(w * h / maxNumOfPixels));
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
	            .floor(w / minSideLength), Math.floor(h / minSideLength));

	    if (upperBound < lowerBound) {
	        // return the larger one when there is no overlapping zone.
	        return lowerBound;
	    }

	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
	        return 1;
	    } else if (minSideLength == -1) {
	        return lowerBound;
	    } else {
	        return upperBound;
	    }
	}
}