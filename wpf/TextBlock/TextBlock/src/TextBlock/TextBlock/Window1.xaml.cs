﻿using System;
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

namespace TextBlock
{
    /// <summary>
    /// Window1.xaml の相互作用ロジック
    /// </summary>
    public partial class Window1 : Window
    {
        public Window1()
        {
            InitializeComponent();
        }

        // button1がクリックされたとき.
        private void button1_Click(object sender, RoutedEventArgs e)
        {

            // "ABCDE"を表示.
            textBlock1.Text = "ABCDE";  // textBlock1.Textに"ABCDE"をセット.

        }
    }
}
