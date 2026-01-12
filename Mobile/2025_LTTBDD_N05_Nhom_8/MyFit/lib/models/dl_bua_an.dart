class MealsListData {
  String imagePath;
  String title;
  String startColor;
  String endColor;
  List<String> meals;
  int kcal;

  MealsListData({
    this.imagePath = '',
    this.title = '',
    this.startColor = '',
    this.endColor = '',
    required this.meals,
    this.kcal = 0,
  });

  static List<MealsListData> tabIconsList = [
    MealsListData(
      imagePath: 'lib/assets/images/breakfast.png',
      title: 'Bữa sáng',
      startColor: '#FA7D82',
      endColor: '#FFB295',
      kcal: 525,
      meals: ['Bánh mì', 'Bơ đậu phộng', 'Táo'],
    ),
    MealsListData(
      imagePath: 'lib/assets/images/lunch.png',
      title: 'Bữa trưa',
      startColor: '#738AE6',
      endColor: '#5C5EDD',
      kcal: 602,
      meals: ['Cá hồi', 'Rau trộn', 'Bơ'],
    ),
    MealsListData(
      imagePath: 'lib/assets/images/snack.png',
      title: 'Bữa ăn nhẹ',
      startColor: '#FE95B6',
      endColor: '#FF5287',
      kcal: 0,
      meals: ['Gợi ý', '800 kcal'],
    ),
    MealsListData(
      imagePath: 'lib/assets/images/dinner.png',
      title: 'Bữa tối',
      startColor: '#6F72CA',
      endColor: '#1E1466',
      kcal: 0,
      meals: ['Gợi ý', '703 kcal'],
    ),
  ];
}
