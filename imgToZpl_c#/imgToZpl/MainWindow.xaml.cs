using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Drawing;

namespace imgToZpl
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 转换按钮事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            string filePath = this.imgPath.Text;
            if (filePath == null || filePath.Length == 0)
            {
                MessageBox.Show("请选择图片");
            }
            else
            {
                Bitmap imageToConvert = (Bitmap)Bitmap.FromFile(filePath);
                string str = Image2ZPL.Convert.BitmapToZPLII(imageToConvert, 20, 20);
                this.zplCode.Text = str;
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            var openFileDialog = new Microsoft.Win32.OpenFileDialog()
            {
                Filter = "Excel Files (*.bmp, *.png,*.jpg)|*.bmp;*.jpg;*.png"
            };
            var result = openFileDialog.ShowDialog();
            if (result == true)
            {
                this.imgPath.Text = openFileDialog.FileName;
            }
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void imgPath_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }
}
