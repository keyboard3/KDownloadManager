package com.keyboard3;

import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * @author keyboard3 on 2017/11/24
 */

public class KDownloader {
    private final BuilderParams params;

    protected KDownloader(BuilderParams params) {
        this.params = params;
    }

    protected void go() {
        if (params == null || params.wra.get() == null) {
            return;
        }
        Context activity = params.wra.get();
        Intent intent = new Intent(activity, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, params.downloadUrl);
        intent.putExtra(DownloadService.BUNDLE_KEY_APP_NAME, params.appName);
        intent.putExtra(DownloadService.BUNDLE_KEY_APK_NAME, params.apkName);
        intent.putExtra(DownloadService.BUNDLE_KEY_VERSION_NAME, params.versionName);
        intent.putExtra(DownloadService.BUNDLE_KEY_APK_DIR, params.apkDir);
        intent.putExtra(DownloadService.BUNDLE_KEY_INSTALL, params.install);
        intent.putExtra(DownloadService.BUNDLE_KEY_SYSTEM_DOWNLOAD, params.systemDownload);
        activity.startService(intent);
    }

    static class BuilderParams {
        public WeakReference<Context> wra;
        public String downloadUrl;
        public String apkDir;
        public String versionName;
        public String appName;
        public String apkName;
        public boolean install = true;
        public boolean systemDownload = true;
    }

    public static class Builder {
        private final BuilderParams params;

        public Builder(Context context) {
            params = new BuilderParams();
            params.wra = new WeakReference<Context>(context);
        }

        public Builder setDownloadUrl(String url) {
            params.downloadUrl = url;
            return this;
        }

        public Builder setApkDir(String apkDir) {
            params.apkDir = apkDir;
            return this;
        }

        public Builder setInstall(boolean isInstall) {
            params.install = isInstall;
            return this;
        }

        public Builder setSystemDownload(boolean systemDownload) {
            params.systemDownload = systemDownload;
            return this;
        }

        public Builder setVersionName(String versionName) {
            params.versionName = versionName;
            return this;
        }

        public Builder setAppName(String appName) {
            params.appName = appName;
            return this;
        }

        public Builder setApkName(String apkName) {
            params.apkName = apkName;
            return this;
        }

        private KDownloader create() {
            return new KDownloader(params);
        }

        public KDownloader start() {
            KDownloader kDownloadManager = create();
            kDownloadManager.go();
            return kDownloadManager;
        }
    }
}