using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using Pracamagisterksa.Resources;
using System.Data.Linq;

namespace Pracamagisterksa
{
    public partial class MainPage : PhoneApplicationPage
    {
        private dbContext db;
        // Constructor
        public MainPage()
        {
            InitializeComponent();

            // Set the data context of the listbox control to the sample data
            DataContext = App.ViewModel;
            db = new dbContext(dbContext.ConnectionString);
            // Sample code to localize the ApplicationBar
            //BuildLocalizedApplicationBar();


        }

        // Load data for the ViewModel Items
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            //pivot.Title = "Praca Magisterska    " + global_settings.Chosed_table.ToString(); 
        }

        private void Simple_insert_click(object sender, RoutedEventArgs e)
        {
           execution_time_inserts.Text = global_settings.Tbi.simple_insert_rows(Int32.Parse(rows_number_inserts.Text)).ToString()+"ms";

        }

        private void Transaction_insert_click(object sender, RoutedEventArgs e)
        {
            execution_time_inserts.Text = global_settings.Tbi.simple_insert_rows_transaction(Int32.Parse(rows_number_inserts.Text)).ToString() + "ms";
        }


        private void Simple_update_click(object sender, RoutedEventArgs e)
        {
            execution_time_updates.Text = global_settings.Tbi.simple_update_rows(Int32.Parse(rows_number_updates.Text)).ToString() + "ms";
        }

        private void Transaction_update_click(object sender, RoutedEventArgs e)
        {
            execution_time_updates.Text = global_settings.Tbi.simple_update_rows_transaction(Int32.Parse(rows_number_updates.Text)).ToString() + "ms";
        }

        private void Simple_delete_click(object sender, RoutedEventArgs e)
        {
            execution_time_deletes.Text = global_settings.Tbi.simple_delete_rows(Int32.Parse(rows_number_deletes.Text)).ToString() + "ms";
        }

        private void Transaction_delete_click(object sender, RoutedEventArgs e)
        {
            execution_time_deletes.Text = global_settings.Tbi.simple_delete_rows_transaction(Int32.Parse(rows_number_deletes.Text)).ToString() + "ms";
        }


        private void Delete_all_click(object sender, RoutedEventArgs e)
        {
            execution_time_deletes.Text = global_settings.Tbi.delete_all_rows().ToString() + "ms";
        }
        


        private void Simple_select_click(object sender, RoutedEventArgs e)
        {
            execution_time_selects.Text = global_settings.Tbi.simple_select_rows(Int32.Parse(rows_number_selects.Text)).ToString() + "ms";

            TABLE_WITH_1_COLUMN item;
            var selectQuery =
            (from w in db.TABLE_WITH_1_COLUMN
             select w).Take(Int32.Parse(rows_number_selects.Text));

            int k = 0;

            foreach (var row in selectQuery)
            {
                k++;
                item = row;
                test.Text = item.Integer1.ToString();
            }
        }

        private void Simple_select_ordered_click(object sender, RoutedEventArgs e)
        {
            execution_time_selects.Text = global_settings.Tbi.simple_select_rows_ordered(Int32.Parse(rows_number_selects.Text)).ToString() + "ms";
        }

        private void Set_active_1_click(object sender, RoutedEventArgs e)
        {
            global_settings.Chosed_table = table_name.TABLE_WITH_1_COLUMN;
            pivot.Title = global_settings.Chosed_table.ToString(); 
        }

        private void Set_active_2_click(object sender, RoutedEventArgs e)
        {
            global_settings.Chosed_table = table_name.TABLE_WITH_2_COLUMNS;
            pivot.Title = global_settings.Chosed_table.ToString(); 
        }
        private void Set_active_3_click(object sender, RoutedEventArgs e)
        {
            global_settings.Chosed_table = table_name.TABLE_WITH_5_COLUMNS;
            pivot.Title = global_settings.Chosed_table.ToString(); 
        }
        private void Set_active_4_click(object sender, RoutedEventArgs e)
        {
            global_settings.Chosed_table = table_name.SIMPLE_RELATION_TABLES;
            pivot.Title = global_settings.Chosed_table.ToString(); 
        }

        // Sample code for building a localized ApplicationBar
        //private void BuildLocalizedApplicationBar()
        //{
        //    // Set the page's ApplicationBar to a new instance of ApplicationBar.
        //    ApplicationBar = new ApplicationBar();

        //    // Create a new button and set the text value to the localized string from AppResources.
        //    ApplicationBarIconButton appBarButton = new ApplicationBarIconButton(new Uri("/Assets/AppBar/appbar.add.rest.png", UriKind.Relative));
        //    appBarButton.Text = AppResources.AppBarButtonText;
        //    ApplicationBar.Buttons.Add(appBarButton);

        //    // Create a new menu item with the localized string from AppResources.
        //    ApplicationBarMenuItem appBarMenuItem = new ApplicationBarMenuItem(AppResources.AppBarMenuItemText);
        //    ApplicationBar.MenuItems.Add(appBarMenuItem);
        //}
    }
}