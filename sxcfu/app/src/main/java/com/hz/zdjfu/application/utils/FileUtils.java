package com.hz.zdjfu.application.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * [类功能说明]
 *文件工具
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/7/25 0025
 */

public class FileUtils {

    private final static String TAG = "FILESERVER";

    public File ImgCachePath;
    public File ImgSavePath;
    public File ImgSavePathPhoto;
    public File ImgThumbnailSavePath;
    public File ImgSharePath;
    public File ApkSavePath;
    public File LogSavePath;
    public File ImgCapTempPath;
    public File ImgCapCutPath;
    public File ImgCacheDefaultPath;
    public File VoiceCachePath;
    public File HeadImage_path;
    public File XLPublishCachePath;
    public File DataCache;
    /**
     * 设备唯一标识did存储路径
     */
    public File Did_path;
    /**
     * webview照片存储路径
     */
    public File Browser_photo_path;

    public static String APP_DATA_ROOT_PATH;
    public static String IMG_SAVE_PATH;
    public static String IMG_SAVE_PATH_PHOTO; //用户拍照原图
    public static String IMG_THUMBNAIL_SAVE_PATH;
    public static String IMG_SHARE_PATH;
    public static String APK_INSTALL_PATH;
    public static String APK_LOG_PATH;
    public static String IMG_SAVE_PATH_CAP_TEMP;
    public static String IMG_SAVE_PATH_CAP_CUT;
    public static String IMG_CACHE_XUTILS_SDCARD_PATH;
    public static String IMG_CACHE_XUTILS_DEFAULT_PATH;
    public static String IMG_CACHE_HEADIMAGE_PATH;
    public static String FINAL_IMAGE_PATH;
    public static String FINAL_TEMP_PATH;
    public static String SDPATH;
    public static String VOICE_CACHE_PATH;

    public static String DATA_CACHE;
    /**
     * 设备唯一标识did存储路径
     */
    public static String DID_PATH;
    /**
     * webview 照片存储路径
     */
    public static String BROWSER_PHOTO_PATH;
    
    public File HaoZhuoKeJiPath;
    public Context mContext;
    private static FileUtils mInstance;

    public FileUtils(Context context) {
        mContext = context;
    }

    /**
     * 创建文件工具类示例
     *
     * @param context 上下文
     * @return
     */
    public static synchronized FileUtils createInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FileUtils(context);
            mInstance.initPath();
        }
        return mInstance;
    }

    /**
     * 获取文件工具类实例
     *
     * @return
     */
    public static synchronized FileUtils getInstance() {
        if (mInstance == null)
            throw new IllegalStateException("FileUtil must be create by call createInstance(Context context)");
        return mInstance;
    }

    /**
     * 初始化本地缓存路径
     */
    public void initPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            SDPATH = Environment.getExternalStorageDirectory() + "/";
            IMG_SAVE_PATH = SDPATH + "zdjfu/ylf/visitor/images/save/";
            IMG_SAVE_PATH_PHOTO = SDPATH + "zdjfu/ylf/visitor/images/save/photo/";
            IMG_THUMBNAIL_SAVE_PATH = IMG_SAVE_PATH + "thumbnail/";
            IMG_SHARE_PATH = SDPATH + "zdjfu/ylf/visitor/images/share/";
            APK_INSTALL_PATH = SDPATH + "zdjfu/ylf/visitor/app/";
            APK_LOG_PATH = SDPATH + "zdjfu/ylf/visitor/log/";
            IMG_SAVE_PATH_CAP_TEMP = SDPATH + "zdjfu/ylf/visitor/images/save/capture/XiangLin_temp/";
            IMG_SAVE_PATH_CAP_CUT = SDPATH + "zdjfu/ylf/visitor/images/save/capture/XiangLin_cut/";
            IMG_CACHE_XUTILS_SDCARD_PATH = SDPATH + "zdjfu/ylf/visitor/images/cache/";// 用于保存图片缓存吧
            IMG_CACHE_XUTILS_DEFAULT_PATH = SDPATH + "Android/data/" + mContext.getPackageName() + "/cache/imgCache/";

            IMG_CACHE_HEADIMAGE_PATH = SDPATH + "zdjfu/ylf/visitor/images/save/headimage/";//保存用户头像

            VOICE_CACHE_PATH = SDPATH + "zdjfu/ylf/visitor/voice/cache/";

            DID_PATH = SDPATH + ".did/";

            BROWSER_PHOTO_PATH = SDPATH + "zdjfu/ylf/visitor/images/save/browser-photos/";

            APP_DATA_ROOT_PATH = getAppPath() + "zdjfu/ylf/visitor/";
            FINAL_IMAGE_PATH = APP_DATA_ROOT_PATH + "images/";
            FINAL_TEMP_PATH = APP_DATA_ROOT_PATH + "temp/";

            HaoZhuoKeJiPath = new File(APP_DATA_ROOT_PATH);
            if (!HaoZhuoKeJiPath.exists()) {
                HaoZhuoKeJiPath.mkdirs();
            }
            HaoZhuoKeJiPath = new File(FINAL_IMAGE_PATH);
            if (!HaoZhuoKeJiPath.exists()) {
                HaoZhuoKeJiPath.mkdirs();
            }

            HaoZhuoKeJiPath = new File(FINAL_TEMP_PATH);
            if (!HaoZhuoKeJiPath.exists()) {
                HaoZhuoKeJiPath.mkdirs();
            }

            // 拍照图片保存地址
            ImgCapTempPath = new File(IMG_SAVE_PATH_CAP_TEMP);
            if (!ImgCapTempPath.exists()) {
                ImgCapTempPath.mkdirs();
            }
            // 裁剪后图片保存地址
            ImgCapCutPath = new File(IMG_SAVE_PATH_CAP_CUT);
            if (!ImgCapCutPath.exists()) {
                ImgCapCutPath.mkdirs();
            }
            // 图片保存、缓存地址
            ImgSavePath = new File(IMG_SAVE_PATH);
            if (!ImgSavePath.exists()) {
                ImgSavePath.mkdirs();
            }
            // 用户拍照图片保存、缓存地址
            ImgSavePathPhoto = new File(IMG_SAVE_PATH_PHOTO);
            if (!ImgSavePathPhoto.exists()) {
                ImgSavePathPhoto.mkdirs();
            }
            // 图片缩略图保存地址
            ImgThumbnailSavePath = new File(IMG_THUMBNAIL_SAVE_PATH);
            if (!ImgThumbnailSavePath.exists()) {
                ImgThumbnailSavePath.mkdirs();
            }

            // 分享图片的临时保存路径
            ImgSharePath = new File(IMG_SHARE_PATH);
            if (!ImgSharePath.exists()) {
                ImgSharePath.mkdirs();
            }
            // 检测更新时保存路径
            ApkSavePath = new File(APK_INSTALL_PATH);
            if (!ApkSavePath.exists()) {
                ApkSavePath.mkdirs();
            }
            // 异常保存路径
            LogSavePath = new File(APK_LOG_PATH);
            if (!LogSavePath.exists()) {
                LogSavePath.mkdirs();
            }
            ImgCachePath = new File(IMG_CACHE_XUTILS_SDCARD_PATH);
            if (!ImgCachePath.exists()) {
                ImgCachePath.mkdirs();
            }
            ImgCacheDefaultPath = new File(IMG_CACHE_XUTILS_DEFAULT_PATH);
            if (!ImgCacheDefaultPath.exists()) {
                ImgCacheDefaultPath.mkdirs();
            }

            // 头像保存路径
            HeadImage_path = new File(IMG_CACHE_HEADIMAGE_PATH);
            if (!HeadImage_path.exists()) {
                HeadImage_path.mkdirs();
            }

            // 设备唯一标识存储路径
            Did_path = new File(DID_PATH);
            if (!Did_path.exists()) {
                Did_path.mkdirs();
            }

            // webview 照片存储路径
            Browser_photo_path = new File(BROWSER_PHOTO_PATH);
            if (!Browser_photo_path.exists()) {
                Browser_photo_path.mkdirs();
            }

            //信息缓存
//            DataCache = new File(DATA_CACHE);
//            if(!DataCache.exists()){
//                DataCache.mkdirs();
//            }
            DataCache = new File(mContext.getExternalCacheDir(),"ZdjfuCache");



        }

    }

    private String getAppPath() {
        LogUtils.i("MyApplication-getAppPath():" + mContext.getFilesDir().getParent());
        return mContext.getFilesDir().getParent() + "/";
    }

    private String getPathFiles() {
        LogUtils.i("MyApplication-getPathFiles():" + mContext.getFilesDir().getAbsolutePath());
        return mContext.getFilesDir().getAbsolutePath() + "/";
    }

    /**
     * [将文件保存到SDcard方法]<BR>
     * [功能详细描述]
     * @param fileName
     * @throws IOException
     */
    public boolean saveFile2SDCard(String fileName, byte[] dataBytes) throws IOException {
        boolean flag = false;
        FileOutputStream fs = null;
        try {
            if (!TextUtils.isEmpty(fileName)) {
                File file = newFileWithPath(fileName.toString());
                if (file.exists()) {
                    file.delete();
                    LogUtils.w("httpFrame  threadName:" + Thread.currentThread().getName() + " 文件已存在 则先删除: "
                            + fileName.toString());
                }
                fs = new FileOutputStream(file);
                fs.write(dataBytes, 0, dataBytes.length);
                fs.flush();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null)
                fs.close();
        }

        return flag;
    }

    /**
     * 创建一个文件，如果其所在目录不存在时，他的目录也会被跟着创建
     */
    public File newFileWithPath(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        int index = filePath.lastIndexOf(File.separator);
        String path = "";
        if (index != -1) {
            path = filePath.substring(0, index);
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path.toString());
                // 如果文件夹不存在
                if (!file.exists() && !file.isDirectory()) {
                    boolean flag = file.mkdirs();
                    if (flag) {
                        LogUtils.i("httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹成功："
                                + file.getPath());
                    } else {
                        LogUtils.e("httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹失败："
                                + file.getPath());
                    }
                }
            }
        }
        return new File(filePath);
    }

    /**
     * 判断文件是否存在
     * @param strPath
     * @return
     */
    public boolean isExists(String strPath) {
        if (strPath == null) {
            return false;
        }

        final File strFile = new File(strPath);

        if (strFile.exists()) {
            return true;
        }
        return false;

    }

    public boolean deleteFile(String strPath) {
        if (strPath == null) {
            return false;
        }

        final File strFile = new File(strPath);

        if (strFile.exists()) {
            return strFile.delete();
        }
        return false;

    }

//    /**
//     * 下载文件
//     *
//     * @param context
//     * @param xlid
//     * @param fileId  需要下载的文件ID
//     * @param path    保存的路径
//     */
//    public static void downloadFile(Context context, long xlid, String fileId, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG, "要下载的文件ID" + fileId);
//        if(fileId != null && !fileId.equals("null")) {
//            FileNetWork.getInstance()
//                    .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                            .XLID(xlid)
//                            .fileID(fileId)   //test:"569"
//                            .fileToPath(path)     //test :    "/sdcard/"
//                            .fileStatus(FileNetWork.FILE_DOWNLOAD)
//                            .mContext(context)
//                            .listener(listener)
//                            .build());
//        }else{
//            LogCatLog.d(TAG,"文件ID 为null");
//        }
//    }
//
//
//
//    /**
//     * 下载文件
//     *
//     * @param context
//     * @param xlid
//     * @param fileId  需要下载的文件ID
//     * @param path    保存的路径
//     */
//    public  void downloadFiles(Context context, long xlid, String fileId, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG, "要下载的文件ID" + fileId);
//        if(fileId != null && !fileId.equals("null")) {
//            FileNetWork.getInstance()
//                    .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                            .XLID(xlid)
//                            .fileID(fileId)   //test:"569"
//                            .fileToPath(path)     //test :    "/sdcard/"
//                            .fileStatus(FileNetWork.FILE_DOWNLOAD)
//                            .mContext(context)
//                            .listener(listener)
//                            .build());
//        }else{
//            LogCatLog.d(TAG,"文件ID 为null");
//        }
//    }
//    /**
//     * 文件上传
//     *
//     * @param context
//     * @param path    需上传的文件（路径加文件名）
//     * @param xlid
//     */
//    public static void uploadFile(Context context, long xlid, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG,"需要上传文件的"+path);
//       if (path != null && !path.equals("")) {
//           File f = new File(path);
//           File file = new File(f.getAbsoluteFile() + "");
//           FileNetWork.getInstance()
//                   .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                           .mContext(context)
//                           .XLID(xlid)
//                           .fileStatus(FileNetWork.FILE_UPLOAD)
//                           .fileName(file.getName())
//                           .filePath(file.getAbsolutePath())
//                           .fileSize(file.length())
//                           .token(MD5FileUtil.getFileMD5String(file))
//                           .fileType("1")
//                           .listener(listener)
//                           .build());
//       }else{
//           LogCatLog.d(TAG,"要上传的文件路径不能为null");
//       }
//    }


    /**
     * 拷贝一个文件到另一个目录
     */
    public boolean copyFile(String from, String to) {

        File fromFile, toFile;
        fromFile = new File(from);
        toFile = new File(to);
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            toFile.createNewFile();
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            int bytesRead;
            byte[] buf = new byte[4 * 1024];  // 4K buffer
            while ((bytesRead = fis.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;

    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean removeFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                return file.delete();
            } else {
                LogUtils.d("删除文件失败");
            }
        } catch (Exception e) {
            LogUtils.e("删除文件失败" + e);
        }
        return false;
    }


    //===============h5上传图片===================================


    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static String getCachePath(Context context, @NonNull String dir) {
        boolean sdCardExist = Environment.getExternalStorageState().equals("mounted");
        File cacheDir = context.getExternalCacheDir();
        if (!sdCardExist || cacheDir == null || !cacheDir.exists() || !cacheDir.mkdirs()) {
            cacheDir = context.getCacheDir();
        }

        File tarDir = new File(cacheDir.getPath() + File.separator + dir);
        if (!tarDir.exists()) {
            boolean result = tarDir.mkdir();
            LogUtils.w(TAG, "getCachePath = " + tarDir.getPath() + ", result = " + result);
            if (!result) {
                tarDir = new File("/sdcard/cache/" + dir);
                if (!tarDir.exists()) {
                    result = tarDir.mkdirs();
                }

                LogUtils.e(TAG, "change path = " + tarDir.getPath() + ", result = " + result);
            }
        }

        return tarDir.getPath();
    }

    /**
     * 关闭流
     */
    @SuppressWarnings("WeakerAccess")
    public static void close(Closeable... closeables) {
        if (closeables == null || closeables.length == 0)
            return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝文件
     * 如果目标文件不存在将会自动创建
     *
     * @param srcFile  原文件
     * @param saveFile 目标文件
     * @return 是否拷贝成功
     */
    public static boolean copyFile(final File srcFile, final File saveFile) {
        File parentFile = saveFile.getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs())
                return false;
        }

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(srcFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(saveFile));
            byte[] buffer = new byte[1024 * 4];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(inputStream, outputStream);
        }
        return true;
    }

    private File getDiskCacheDir(Context context, String uniqueName)
    {
        String cachePath;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable())
        {
            //如果SD卡存在通过getExternalCacheDir()获取路径，
            cachePath = context.getExternalCacheDir().getPath();
        } else
        {
            //如果SD卡不存在通过getCacheDir()获取路径，
            cachePath = context.getCacheDir().getPath();
        }
        //放在路径 /.../data/<application package>/cache/uniqueName
        return new File(cachePath + File.separator + uniqueName);
    }

}
