using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pracamagisterksa 
{
    class table1_tasks : Table_interface
    {
        private dbContext db;
        System.Diagnostics.Stopwatch sw;
        

        public table1_tasks()
        {
            db = new dbContext(dbContext.ConnectionString);
            sw = new System.Diagnostics.Stopwatch();
        }

        public long simple_insert_rows(int row_count)
        {

            sw.Reset();
            sw.Start();

            TABLE_WITH_1_COLUMN item;
            for (int i = 0; i < row_count; i++)
            {
                item = new TABLE_WITH_1_COLUMN {Integer1=i};
                db.TABLE_WITH_1_COLUMN.InsertOnSubmit(item);
                db.SubmitChanges();
            }
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_insert_rows_transaction(int row_count)
        {

            sw.Reset();
            sw.Start();

            TABLE_WITH_1_COLUMN item;
            for (int i = 0; i < row_count; i++)
            {
                item = new TABLE_WITH_1_COLUMN { Integer1 = i };
                db.TABLE_WITH_1_COLUMN.InsertOnSubmit(item);
                
            }
            db.SubmitChanges();
            sw.Stop();

            return sw.ElapsedMilliseconds;

        }

        public long simple_update_rows(int row_count)
        {
            sw.Reset();
            sw.Start();


            for (int i = 1; i < row_count; i++)
            {
                TABLE_WITH_1_COLUMN item = (from w in db.TABLE_WITH_1_COLUMN where w.Id >= i select w ).First();
                item.Integer1 = i + 1;
                db.SubmitChanges();
            }

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_update_rows_transaction(int row_count)
        {
            sw.Reset();
            sw.Start();


            for (int i = 1; i <= row_count; i++)
            {
                TABLE_WITH_1_COLUMN item = (from w in db.TABLE_WITH_1_COLUMN where w.Id >= i select w).First();
                item.Integer1 = i + 1;

            }
            
            db.SubmitChanges();
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_select_rows(int row_count)
        {
            sw.Reset();
            sw.Start();
            TABLE_WITH_1_COLUMN item;
            var selectQuery =
            (from w in db.TABLE_WITH_1_COLUMN
            select w).Take(row_count);

            foreach (var row in selectQuery)
            {
                item = row;
            }

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_select_rows_ordered(int row_count)
        {
            sw.Reset();
            sw.Start();
            TABLE_WITH_1_COLUMN item;
            var selectQuery =
            (from w in db.TABLE_WITH_1_COLUMN orderby w.Integer1
             select w).Take(row_count);

            foreach (var row in selectQuery)
            {
                item = row;
            }

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_delete_rows(int row_count)
        {
            sw.Reset();
            sw.Start();


            for (int i = 0; i < row_count; i++)
            {
                TABLE_WITH_1_COLUMN item = (from TABLE_WITH_1_COLUMN w in db.TABLE_WITH_1_COLUMN select w).First();
                db.TABLE_WITH_1_COLUMN.DeleteOnSubmit(item);
                db.SubmitChanges();
            }
            
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_delete_rows_transaction(int row_count)
        {
            sw.Reset();
            sw.Start();


            for (int i = 0; i < row_count; i++)
            {
                TABLE_WITH_1_COLUMN item = (from w in db.TABLE_WITH_1_COLUMN select w).First();
                db.TABLE_WITH_1_COLUMN.DeleteOnSubmit(item); 
            }
            db.SubmitChanges();

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long delete_all_rows()
        {

            sw.Reset();
            sw.Start();
            var selectQuery =
            (from w in db.TABLE_WITH_1_COLUMN
            select w);

            db.TABLE_WITH_1_COLUMN.DeleteAllOnSubmit(selectQuery);
            db.SubmitChanges();

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

    }
}
