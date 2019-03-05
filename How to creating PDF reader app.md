## How to Creating PDF reader app in android Studio
##### By Felipe Prieto

1. You need to create a empty activity.
2. Go to the section build.gradle
![enlace1](https://i.ibb.co/MkHd2cL/1.png)
3. In this section you need to go this link:
[Android Pdf Viewer](https://github.com/barteksc/AndroidPdfViewer)
And copy this text:
```android
implementation 'com.github.barteksc:android-pdf-viewer:3.1.0-beta.1'
```
4. Then, you need to sync up your gradle to update your library.
5. You go to activity.xml and if you need, use Linera Layout, but you need to add this lines:
##### Include PDFView in your layout
```android
<com.github.barteksc.pdfviewer.PDFView
        android:id="@+id/pdfView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
6. Create the assets folder and place a PDF in it
![enlace2](https://i.ibb.co/Kx7hXyf/2.png)
7. After you add your PDF File, you go to your class and add this code:
```android
PDFView pdfView = findViewById(R.id.pdfView);
```
8. It depends on where you want to take your PDF by URL or by saving it, you will need this code:
```android
pdfView.fromUri(Uri)
or
pdfView.fromFile(File)
or
pdfView.fromBytes(byte[])
or
pdfView.fromStream(InputStream) // stream is written to bytearray - native code cannot use Java Streams
or
pdfView.fromSource(DocumentSource)
or
pdfView.fromAsset(String)
    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
    .enableSwipe(true) // allows to block changing pages using swipe
    .swipeHorizontal(false)
    .enableDoubletap(true)
    .defaultPage(0)
    // allows to draw something on the current page, usually visible in the middle of the screen
    .onDraw(onDrawListener)
    // allows to draw something on all pages, separately for every page. Called only for visible pages
    .onDrawAll(onDrawListener)
    .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
    .onPageChange(onPageChangeListener)
    .onPageScroll(onPageScrollListener)
    .onError(onErrorListener)
    .onPageError(onPageErrorListener)
    .onRender(onRenderListener) // called after document is rendered for the first time
    // called on single tap, return true if handled, false to toggle scroll handle visibility
    .onTap(onTapListener)
    .onLongPress(onLongPressListener)
    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
    .password(null)
    .scrollHandle(null)
    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
    // spacing between pages in dp. To define spacing color, set view background
    .spacing(0)
    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
    .linkHandler(DefaultLinkHandler)
    .pageFitPolicy(FitPolicy.WIDTH)
    .pageSnap(true) // snap pages to screen boundaries
    .pageFling(false) // make a fling change only a single page like ViewPager
    .nightMode(false) // toggle night mode
    .load();
```
10. You have some mistakes, you need to delete some lines, i show you what lines you should delete  and how it should be.
    This ss is if you use a URL from the document.
    ![enlace3](https://i.ibb.co/qYNs10z/3.png)
If you usee a asset how it should be
![enlace3](https://i.ibb.co/wWWvVS2/4.png)
## Resources
* The tutorial that i used is this: [Creating PDF reader app - Android Studio Tutorial](https://www.youtube.com/watch?v=6tKLSk8ikmg)
* The library that i used and contributions to the author [barteksc/AndroidPdfViewer
](https://github.com/barteksc/AndroidPdfViewer)