using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pracamagisterksa
{
    public interface Table_interface
    {


        long simple_insert_rows(int row_count);

        long simple_insert_rows_transaction(int row_count);

        long simple_update_rows(int row_count);

        long simple_update_rows_transaction(int row_count);

        long simple_select_rows(int row_count);

        long simple_select_rows_ordered(int row_count);

        long simple_delete_rows(int row_count);

        long simple_delete_rows_transaction(int row_count);


        long delete_all_rows();


    }
}
