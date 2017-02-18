# FloatBackground  

Floating item in background, support text, iamge, custom view  
在背景上悬浮物体，并自由随机移动，支持文字，图片，自定义画图  

## 预览图 Screenshots
![demo1](/screenshot/floatbackground.gif) 

## 集成 Integrate

*  将[library](/library)导入到工程中  // import [library](/library) as a model to your project
  
*  或者将[library](/library)中的`FloatBackground.java`，`FloatObject.java`复制到你的项目中去 // Or copy `FloatBackground.java `,`FloatObject.java` from [library](/library) to your project

## 使用 Usage
#### FloatBackground
FloatBackground继承自FrameLayout，在使用时添加`FloatBackground` 到你的布局文件中，并作为最底层的ViewGroup，这样在其之上的视图都可以以FloatBackground为一层背景显示浮动动画。

FloatBackground is inhert from FrameLayout, add `FloatBackground` to your layout XML file, and set it in the lowest layer in your layout.

```XML
<com.dean.library.FloatBackground
        android:id="@+id/float_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_gravity="center"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <Button
                android:id="@+id/start"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="Start" />

            <Button
                android:id="@+id/end"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="End" />
        </LinearLayout>
    </com.dean.library.FloatBackground>
```

#### FloatObject  
漂浮对象，初始化漂浮对象，继承FloatObject，并重写drawFloatObject方法。
```
public class FloatText extends FloatObject {
    String text;

    public FloatText(float posX, float posY, String text) {
        super(posX, posY);
        this.text = text;
        setAlpha(88);
        setColor(Color.WHITE);
    }

    @Override
    public void drawFloatObject(Canvas canvas, float x, float y, Paint paint) {
        paint.setTextSize(65);
        canvas.drawText(text, x, y, paint);
    }
}
```

#### 将FloatObject添加到FloatBackground
```
FloatBackground floatBackground = (FloatBackground) this.findViewById(R.id.float_view);
floatBackground.addFloatView(new FloatText( 0.3f, 0.6f, "E"));
```

## License
```
Copyright 2016 Dean Guo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
