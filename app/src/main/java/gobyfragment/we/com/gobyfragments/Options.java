package gobyfragment.we.com.gobyfragments;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class Options {

	public static DisplayImageOptions getImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// 设置图片在下载期间显示的图片
				.showImageOnLoading(R.drawable.tools_nomal)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageForEmptyUri(R.drawable.tools_nomal)
				// 设置图片加载/解码过程中错误时候显示的图片
				.showImageOnFail(R.drawable.tools_nomal)
				// 设置下载的图片是否缓存在内存中
				.cacheInMemory(true)
				// 保留Exif信息
				.considerExifParams(true)
				// 设置图片以如何的编码方式显示
				// 设置图片的解码类型
				.bitmapConfig(Bitmap.Config.RGB_565)
				// 设置图片的解码配置
				.imageScaleType(ImageScaleType.EXACTLY)
				// 设置图片在下载前是否重置，复位
				.resetViewBeforeLoading(true)
				// 淡入
				.displayer(new SimpleBitmapDisplayer()).build();
		return options;
	}
}