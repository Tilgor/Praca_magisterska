//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.235
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Linq;
using System.Data.Linq.Mapping;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;
using System.IO;
using System.IO.IsolatedStorage;

using Microsoft.Phone.Data.Linq.Mapping;
using Microsoft.Phone.Data.Linq;



[global::System.Data.Linq.Mapping.TableAttribute()]
public partial class SIMPLE_RELATION_2 : INotifyPropertyChanging, INotifyPropertyChanged
{
	
	private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
	
	private int _ID_INT;
	
	private int _SM1_ID;
	
	private int _Integer1;
	
	private EntityRef<SIMPLE_RELATION_1> _SIMPLE_RELATION_1;
	
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnID_INTChanging(int value);
    partial void OnID_INTChanged();
    partial void OnSM1_IDChanging(int value);
    partial void OnSM1_IDChanged();
    partial void OnInteger1Changing(int value);
    partial void OnInteger1Changed();
    #endregion
	
	public SIMPLE_RELATION_2()
	{
		this._SIMPLE_RELATION_1 = default(EntityRef<SIMPLE_RELATION_1>);
		OnCreated();
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_ID_INT", AutoSync=AutoSync.OnInsert, DbType="Int NOT NULL IDENTITY", IsPrimaryKey=true, IsDbGenerated=true)]
	public int ID_INT
	{
		get
		{
			return this._ID_INT;
		}
		set
		{
			if ((this._ID_INT != value))
			{
				this.OnID_INTChanging(value);
				this.SendPropertyChanging();
				this._ID_INT = value;
				this.SendPropertyChanged("ID_INT");
				this.OnID_INTChanged();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_SM1_ID", DbType="Int NOT NULL")]
	public int SM1_ID
	{
		get
		{
			return this._SM1_ID;
		}
		set
		{
			if ((this._SM1_ID != value))
			{
				if (this._SIMPLE_RELATION_1.HasLoadedOrAssignedValue)
				{
					throw new System.Data.Linq.ForeignKeyReferenceAlreadyHasValueException();
				}
				this.OnSM1_IDChanging(value);
				this.SendPropertyChanging();
				this._SM1_ID = value;
				this.SendPropertyChanged("SM1_ID");
				this.OnSM1_IDChanged();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Name="integer1", Storage="_Integer1", DbType="Int NOT NULL")]
	public int Integer1
	{
		get
		{
			return this._Integer1;
		}
		set
		{
			if ((this._Integer1 != value))
			{
				this.OnInteger1Changing(value);
				this.SendPropertyChanging();
				this._Integer1 = value;
				this.SendPropertyChanged("Integer1");
				this.OnInteger1Changed();
			}
		}
	}
	
	[global::System.Runtime.Serialization.IgnoreDataMember]
	[global::System.Data.Linq.Mapping.AssociationAttribute(Name="SM1_key", Storage="_SIMPLE_RELATION_1", ThisKey="SM1_ID", OtherKey="ID_INT", IsForeignKey=true, DeleteOnNull=true)]
	public SIMPLE_RELATION_1 SIMPLE_RELATION_1
	{
		get
		{
			return this._SIMPLE_RELATION_1.Entity;
		}
		set
		{
			SIMPLE_RELATION_1 previousValue = this._SIMPLE_RELATION_1.Entity;
			if (((previousValue != value) 
						|| (this._SIMPLE_RELATION_1.HasLoadedOrAssignedValue == false)))
			{
				this.SendPropertyChanging();
				if ((previousValue != null))
				{
					this._SIMPLE_RELATION_1.Entity = null;
					previousValue.SIMPLE_RELATION_2.Remove(this);
				}
				this._SIMPLE_RELATION_1.Entity = value;
				if ((value != null))
				{
					value.SIMPLE_RELATION_2.Add(this);
					this._SM1_ID = value.ID_INT;
				}
				else
				{
					this._SM1_ID = default(int);
				}
				this.SendPropertyChanged("SIMPLE_RELATION_1");
			}
		}
	}
	
	public event PropertyChangingEventHandler PropertyChanging;
	
	public event PropertyChangedEventHandler PropertyChanged;
	
	protected virtual void SendPropertyChanging()
	{
		if ((this.PropertyChanging != null))
		{
			this.PropertyChanging(this, emptyChangingEventArgs);
		}
	}
	
	protected virtual void SendPropertyChanged(String propertyName)
	{
		if ((this.PropertyChanged != null))
		{
			this.PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
		}
	}
}