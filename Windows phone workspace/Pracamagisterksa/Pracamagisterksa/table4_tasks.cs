using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pracamagisterksa
{
    class table4_tasks : Table_interface
    {
        private dbContext db;
        System.Diagnostics.Stopwatch sw;


        public table4_tasks()
        {
            db = new dbContext(dbContext.ConnectionString);
            sw = new System.Diagnostics.Stopwatch();
        }

        public long simple_insert_rows(int row_count)
        {
            row_count = row_count / 5;
            sw.Reset();
            sw.Start();

            SIMPLE_RELATION_1 item;
            SIMPLE_RELATION_2 item2;
            for (int i = 0; i < row_count; i++)
            {
                item = new SIMPLE_RELATION_1 { Integer1 = i };
                db.SIMPLE_RELATION_1.InsertOnSubmit(item);
                db.SubmitChanges();
                for (int k = 0; k < 4; k++)
                {
                    item2 = new SIMPLE_RELATION_2 { Integer1 = i + k, SM1_ID = item.ID_INT };
                    db.SIMPLE_RELATION_2.InsertOnSubmit(item2);
                    db.SubmitChanges();
                }
            }
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_insert_rows_transaction(int row_count)
        {
            row_count = row_count / 5;
            sw.Reset();
            sw.Start();

            SIMPLE_RELATION_1 item;
            SIMPLE_RELATION_2 item2;
            for (int i = 0; i < row_count; i++)
            {
                item = new SIMPLE_RELATION_1 { Integer1 = i };
                db.SIMPLE_RELATION_1.InsertOnSubmit(item);
                db.SubmitChanges();
                for (int k = 0; k < 4; k++)
                {
                    item2 = new SIMPLE_RELATION_2 { Integer1 = i + k, SM1_ID = item.ID_INT };
                    db.SIMPLE_RELATION_2.InsertOnSubmit(item2);
                }

            }
            db.SubmitChanges();
            sw.Stop();

            return sw.ElapsedMilliseconds;

        }

        public long simple_update_rows(int row_count)
        {
            row_count = row_count / 5;
            sw.Reset();
            sw.Start();


            for (int i = 1; i < row_count; i++)
            {
                SIMPLE_RELATION_1 item = (from w in db.SIMPLE_RELATION_1 where w.ID_INT >= i select w).First();
                item.Integer1 = i + 1;
                db.SubmitChanges();
                for (int k = 0; k < 4; k++)
                {
                    SIMPLE_RELATION_2 item2 = (from w in db.SIMPLE_RELATION_2 where w.ID_INT >= i select w).First();
                    item2.Integer1 = i + k + 1;
                    db.SubmitChanges();
                }
            }

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_update_rows_transaction(int row_count)
        {
            row_count = row_count / 5;
            sw.Reset();
            sw.Start();


            for (int i = 1; i <= row_count; i++)
            {
                SIMPLE_RELATION_1 item = (from w in db.SIMPLE_RELATION_1 where w.ID_INT >= i select w).First();
                item.Integer1 = i + 1;
                for (int k = 0; k < 4; k++)
                {
                    SIMPLE_RELATION_2 item2 = (from w in db.SIMPLE_RELATION_2 where w.ID_INT >= i select w).First();
                    item2.Integer1 = i + k + 1;
                }

            }

            db.SubmitChanges();
            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_select_rows(int row_count)
        {
            int ID_tb1, integer1_tb1, ID_tb2, integer1_tb2;
            sw.Reset();
            sw.Start();
            var selectQuery =
            (from w in db.SIMPLE_RELATION_1 join w1 in db.SIMPLE_RELATION_2 on w.ID_INT equals w1.SM1_ID
             select new
                  {
                      ID_tb1 = w.ID_INT,
                      integer1_tb1 = w.Integer1,
                      ID_tb2 = w1.ID_INT,
                      integer1_tb2 = w1.Integer1
                  });

            foreach (var row in selectQuery)
            {
                ID_tb1 = row.ID_tb1;
                integer1_tb1 = row.integer1_tb1;
                ID_tb2 = row.ID_tb2;
                integer1_tb2 = row.integer1_tb2;

            }

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

        public long simple_select_rows_ordered(int row_count)
        {
            int ID_tb1, integer1_tb1, ID_tb2, integer1_tb2;
            sw.Reset();
            sw.Start();
            var selectQuery =
            (from w in db.SIMPLE_RELATION_1
             join w1 in db.SIMPLE_RELATION_2 on w.ID_INT equals w1.SM1_ID orderby w1.Integer1
             select new
             {
                 ID_tb1 = w.ID_INT,
                 integer1_tb1 = w.Integer1,
                 ID_tb2 = w1.ID_INT,
                 integer1_tb2 = w1.Integer1
             });

            foreach (var row in selectQuery)
            {
                ID_tb1 = row.ID_tb1;
                integer1_tb1 = row.integer1_tb1;
                ID_tb2 = row.ID_tb2;
                integer1_tb2 = row.integer1_tb2;

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
                SIMPLE_RELATION_1 item = (from SIMPLE_RELATION_1 w in db.SIMPLE_RELATION_1 select w).First();
                db.SIMPLE_RELATION_1.DeleteOnSubmit(item);
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
                SIMPLE_RELATION_1 item = (from w in db.SIMPLE_RELATION_1 select w).First();
                db.SIMPLE_RELATION_1.DeleteOnSubmit(item);
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
            (from w in db.SIMPLE_RELATION_1
             select w);

            db.SIMPLE_RELATION_1.DeleteAllOnSubmit(selectQuery);
            db.SubmitChanges();

            sw.Stop();

            return sw.ElapsedMilliseconds;
        }

    }
}
