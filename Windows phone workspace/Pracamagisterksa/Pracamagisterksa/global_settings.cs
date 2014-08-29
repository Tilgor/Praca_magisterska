using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pracamagisterksa
{
    public class global_settings
    {

        private static table_name chosed_table = table_name.TABLE_WITH_1_COLUMN;

        public static table_name Chosed_table 
        {
            get { return global_settings.chosed_table; }
            set {
                if (value == table_name.TABLE_WITH_1_COLUMN)
                    tbi = new table1_tasks();
                if (value == table_name.TABLE_WITH_2_COLUMNS)
                    tbi = new table2_tasks();
                if (value == table_name.TABLE_WITH_5_COLUMNS)
                    tbi = new table3_tasks();
                if (value == table_name.SIMPLE_RELATION_TABLES)
                    tbi = new table4_tasks();

                global_settings.chosed_table = value; }
        }

        private static Table_interface tbi = new table1_tasks();

        public static Table_interface Tbi
        {
            get { return global_settings.tbi; }
            set { global_settings.tbi = value; }
        }





    }
}
