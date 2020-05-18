# imagesCollection
## Описание классов проекта:
1. **NewImage** - класс, хранящий в себе параметры изображения(дату создания, разрешение, средний цвет по всем пикселям изображения и имя этого файла).
2. **ImageCollection** - класс коллекции из картинок, которые считываются из файла с определенным форматом(задает сам пользователь). Этот класс содержит множество функций, позволяющих работать с коллекцией. Во-первых можно вывести все элемент(названия файлов) отсортированного списка. Во-вторых содержит функцию добавления нового изображения, а так же множество функций, вычисляющих расстояние по разным критериям(в том числе и общее по всем).
3. **ImgComparison** - класс описывающий работу компоратора для NewImage класса.
4. **Main** - выполняется алгоритм работы с пользователем(консольное приложение).

Для примера можно использовать изображения в папке images.
