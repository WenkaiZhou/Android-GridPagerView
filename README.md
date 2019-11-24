# GridViewPager

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-1.0.0-5bc0de.svg?style=flat-square)](https://bintray.com/xuehuayous/maven/Android-GridPagerView/_latestVersion)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2014%2B%20-f0ad4e.svg?style=flat-square)](https://android-arsenal.com/api?level=14)

让网格翻页开发变得更简单，配合dataBinding食用，真香！

## 示例图片

<img src="https://raw.githubusercontent.com/xuehuayous/Android-GridPagerView/master/sample/pic/01.gif" width="300" />

## 引入

**androidX**

```
implementation 'com.kevin:gridpagerview:1.0.0'
```

## 如何使用

### 在layout.xml 中配置GridViewPager

```xml
<com.kevin.gridpager.GridViewPager
    android:id="@+id/cate_page"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:overScrollMode="never"/>
```

### 设置条目适配器

这里使用常用方式，推荐使用dataBinding的方式，参考demo中[BindingCategoryAdapter](https://github.com/xuehuayous/Android-GridPagerView/blob/master/sample/src/main/java/com/kevin/gridpager/sample/binding/BindingCategoryAdapter.kt)。

```kotlin
class CategoryAdapter : ClickableGridViewAdapter<Category, CommonCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_common_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, pagePosition: Int, item: Category) {
        super.onBindViewHolder(holder, position, pagePosition, item)
        holder.tvName.text = item.name
    }

    override fun onItemClick(view: View, item: Category, position: Int, pagePosition: Int) {
        Toast.makeText(view.context, "position = $position, pagePosition = $pagePosition, ${item.name}", Toast.LENGTH_SHORT).show()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
    }
}
```

3. 初始化 `GridViewPager`

```kotlin
gridViewPager.setGridViewAdapter(CategoryAdapter())
// 设置数据
val list: MutableList<Category> = mutableListOf()
for (i in 0 until count) {
    list.add(Category("item $i"))
}
gridViewPager.setData(list)
```

## 更多配置

### XML 配置

```xml
<com.kevin.gridpager.GridViewPager
    android:id="@+id/cate_page"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:overScrollMode="never"
    app:gvp_pageColumns="[列数]"
    app:gvp_pageRows="[行数]"/>
```

### 在代码中配置

```kotlin
// 设置单页列数
gridViewPager.setPageColumns(columns)
// 设置单页行数
gridViewPager.setPageRows(rows)
// 设置数据
gridViewPager.setData(list)
```

## License

```text
Copyright (c) 2019 Kevin zhou

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