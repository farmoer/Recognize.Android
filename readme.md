#1.设计方案

从5.0开始（API Level 21），可以完全控制安卓设备相机的新api Camera2 ( android.hardware.Camera2 ) 被引入了进来，本方案基于最新Camera2进行开发。

+ MainActivity: 负责主界面的生命周期管理。
+ Camera2BasicFragment: 负责管理摄像头，预览及处理，识别等。
+ AutoFitTextureView: 负责显示摄像头拍摄画面的预览输出显示。
+ ViewfinderView: 负责绘制扫描窗口，对于摄像头预览画面进行扫描区域绘制。
+ ImageFilter: 图像处理函数集，负责进行图像灰度、直方图均衡、二值化等处理。
+ IOUtils: 存储的处理函数集
+ OCRUtils: 图片OCR识别字符集。

#2.程序依赖项
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:25.0.1'
    compile "com.android.support:support-v4:25.0.1"
    compile "com.android.support:support-v13:25.0.1"
    compile "com.android.support:cardview-v7:25.0.1"
    compile 'com.rmtheis:tess-two:6.1.1'
}
```
#3.图片识别函数实现
```
public static String parseImageToString(String language, Bitmap srcBitmap, String dataPath) throws IOException
{       
    Bitmap bitmap = srcBitmap;
    TessBaseAPI baseApi = new TessBaseAPI();
    baseApi.setDebug(true);
    // 使用默认语言初始化BaseApi
    baseApi.init(dataPath, language); 
    baseApi.setImage(bitmap);
    // 获取返回值
    String recognizedText = baseApi.getUTF8Text();
    baseApi.end();
    return recognizedText;
}
```
#4.扫描窗口绘制部分代码实现
```
@Override
protected void onDraw(Canvas canvas) {
    int width = canvas.getWidth();
    int height = canvas.getHeight();
    mPaint.setColor( mMaskColor);
    canvas.drawRect(0, 0, width, mFrame.top, mPaint);
    canvas.drawRect(0, mFrame.top, mFrame.left, mFrame.bottom + 1, mPaint);
    canvas.drawRect(mFrame.right + 1, mFrame.top, width, mFrame.bottom + 1, mPaint);
    canvas.drawRect(0, mFrame.bottom + 1, width, height, mPaint);
    mPaint.setColor(Color.GREEN);
    canvas.drawRect(mFrame.left, mFrame.top, mFrame.left + mScreenRate, mFrame.top + CORNER_WIDTH, mPaint);
    canvas.drawRect(mFrame.left, mFrame.top, mFrame.left + CORNER_WIDTH, mFrame.top + mScreenRate, mPaint);
    canvas.drawRect(mFrame.right - mScreenRate, mFrame.top, mFrame.right, mFrame.top + CORNER_WIDTH, mPaint);
    canvas.drawRect(mFrame.right - CORNER_WIDTH, mFrame.top, mFrame.right, mFrame.top + mScreenRate, mPaint);
    canvas.drawRect(mFrame.left, mFrame.bottom - CORNER_WIDTH, mFrame.left + mScreenRate, mFrame.bottom, mPaint);
    canvas.drawRect(mFrame.left, mFrame.bottom - mScreenRate, mFrame.left + CORNER_WIDTH, mFrame.bottom, mPaint);
    canvas.drawRect(mFrame.right - mScreenRate, mFrame.bottom - CORNER_WIDTH, mFrame.right, mFrame.bottom, mPaint);
    canvas.drawRect(mFrame.right - CORNER_WIDTH, mFrame.bottom - mScreenRate, mFrame.right, mFrame.bottom, mPaint);
}
```
