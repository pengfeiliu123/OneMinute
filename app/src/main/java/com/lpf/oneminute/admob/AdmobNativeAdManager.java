package com.lpf.oneminute.admob;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.lpf.oneminute.R;

import java.util.List;

public class AdmobNativeAdManager {

//    public static final String ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110";
    public static final String ADMOB_AD_UNIT_ID = "ca-app-pub-7702253890707460/9822004233";
    public static final String ADMOB_APP_ID = "ca-app-pub-7702253890707460~7147739435";

    public static final long AD_TIME = 10 * 60 * 1000l;

    public static void populateAppInstallAdView(NativeAppInstallAd nativeAppInstallAd,
                                                FrameLayout layout) {
        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAppInstallAd.getVideoController();

        // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
        // VideoController will call methods on this object when events occur in the video
        // lifecycle.
        vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            public void onVideoEnd() {
                // Publishers should allow native ads to complete video playback before refreshing
                // or replacing them with another ad in the same UI location.
                super.onVideoEnd();
            }
        });

        NativeAppInstallAdView adView = (NativeAppInstallAdView) layout.findViewById(R.id.nativeAppInstall);

        adView.setHeadlineView(adView.findViewById(R.id.appinstall_headline));
        adView.setCallToActionView(adView.findViewById(R.id.appinstall_call_to_action));
        adView.setIconView(adView.findViewById(R.id.appinstall_app_icon));
//
        // The MediaView will display a video asset if one is present in the ad, and the first image
        // asset otherwise.
        MediaView mediaView = (MediaView) adView.findViewById(R.id.appinstall_media);
        if (mediaView != null) {
            adView.setMediaView(mediaView);
        }

        // Some assets are guaranteed to be in every NativeAppInstallAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
        ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());

        if (nativeAppInstallAd.getIcon() != null && nativeAppInstallAd.getIcon().getDrawable() != null) {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAppInstallAd.getIcon().getDrawable());
        } else {
            adView.getIconView().setVisibility(View.INVISIBLE);
        }


        TextView invisibleView = (TextView) layout.findViewById(R.id.invisible_title);
        invisibleView.setText(nativeAppInstallAd.getHeadline());
        invisibleView.setVisibility(View.INVISIBLE);

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAppInstallAd);
    }

    /**
     * for content ad view
     *
     * @param nativeContentAd
     */
    public static void populateContentAdView(NativeContentAd nativeContentAd,
                                             FrameLayout layout) {
        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeContentAd.getVideoController();

        // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
        // VideoController will call methods on this object when events occur in the video
        // lifecycle.
        vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            public void onVideoEnd() {
                // Publishers should allow native ads to complete video playback before refreshing
                // or replacing them with another ad in the same UI location.
                super.onVideoEnd();
            }
        });

        NativeContentAdView adView = (NativeContentAdView) layout.findViewById(R.id.nativeContentAd);

        adView.setImageView(adView.findViewById(R.id.app_content_media));
        adView.setHeadlineView(adView.findViewById(R.id.app_content_headline));
        adView.setLogoView(adView.findViewById(R.id.app_content_app_icon));
        adView.setCallToActionView(adView.findViewById(R.id.app_content_btn));

        ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());

        List<NativeAd.Image> images = nativeContentAd.getImages();

        if (images != null && images.size() > 0) {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }

        // Some aren't guaranteed, however, and should be checked.
        NativeAd.Image logoImage = nativeContentAd.getLogo();

        if (logoImage == null) {
            adView.getLogoView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getLogoView()).setImageDrawable(logoImage.getDrawable());
            adView.getLogoView().setVisibility(View.VISIBLE);
        }

        TextView invisibleView = (TextView) layout.findViewById(R.id.invisible_title);
        invisibleView.setText(nativeContentAd.getHeadline());
        invisibleView.setVisibility(View.INVISIBLE);
        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);
    }
}
