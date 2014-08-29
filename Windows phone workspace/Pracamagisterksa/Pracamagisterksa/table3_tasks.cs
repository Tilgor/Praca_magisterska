using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pracamagisterksa
{
    class table3_tasks : Table_interface
    {
        private dbContext db;
        System.Diagnostics.Stopwatch sw;


        public table3_tasks()
        {
            db = new dbContext(dbContext.ConnectionString);
            sw = new System.Diagnostics.Stopwatch();
        }

        public long simple_insert_rows(int row_count)
        {

            sw.Reset();
            sw.Start();

            TABLE_WITH_5_COLUMNS item;
            for (int i = 0; i < row_count; i++)
            {
                item = new TABLE_WITH_5_COLUMNS { Integer1 = i, Integer2 = i + 1, Integer3 = i+2, Integer4=i+3,Integer5=i+4};
                db.TABLE_WITH_5_COLUMNS.InsertOnSubmit(item);
                db.SubmitChanges();
            }
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_insert_rows_transaction(int row_count)
        {

            sw.Reset();
            sw.Start();

            TABLE_WITH_5_COLUMNS item;
            for (int i = 0; i < row_count; i++)
            {
                item = new TABLE_WITH_5_COLUMNS { Integer1 = i, Integer2 = i + 1, Integer3 = i + 2, Integer4 = i + 3, Integer5 = i + 4 };
                db.TABLE_WITH_5_COLUMNS.InsertOnSubmit(item);

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
                TABLE_WITH_5_COLUMNS item = (from w in db.TABLE_WITH_5_COLUMNS where w.Id >= i select w).First();
                item.Integer1 = i + 1;
                item.Integer2 = i + 2;
                item.Integer3 = i + 2;
                item.Integer4 = i + 2;
                item.Integer5 = i + 2;
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
                TABLE_WITH_5_COLUMNS item = (from w in db.TABLE_WITH_5_COLUMNS where w.Id >= i select w).First();
                item.Integer1 = i + 1;
                item.Integer2 = i + 2;
                item.Integer3 = i + 2;
                item.Integer4 = i + 2;
                item.Integer5 = i + 2;
            }

            db.SubmitChanges();
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_select_rows(int row_count)
        {
            sw.Reset();
            sw.Start();
            TABLE_WITH_5_COLUMNS item;
            var selectQuery =
            (from w in db.TABLE_WITH_5_COLUMNS
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
            TABLE_WITH_5_COLUMNS item;
            var selectQuery =
            (from w in db.TABLE_WITH_5_COLUMNS
             orderby w.Integer1
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
                TABLE_WITH_5_COLUMNS item = (from TABLE_WITH_5_COLUMNS w in db.TABLE_WITH_5_COLUMNS select w).First();
                db.TABLE_WITH_5_COLUMNS.DeleteOnSubmit(item);
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
                TABLE_WITH_5_COLUMNS item = (from w in db.TABLE_WITH_5_COLUMNS select w).First();
                db.TABLE_WITH_5_COLUMNS.DeleteOnSubmit(item);
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
            (from w in db.TABLE_WITH_5_COLUMNS
             select w);

            db.TABLE_WITH_5_COLUMNS.DeleteAllOnSubmit(selectQuery);
            db.SubmitChanges();

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

    }
}
