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
public partial class TABLE_WITH_5_COLUMNS : INotifyPropertyChanging, INotifyPropertyChanged
{
	
	private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);
	
	private int _Id;
	
	private int _Integer1;
	
	private int _Integer4;
	
	private int _Integer3;
	
	private int _Integer5;
	
	private int _Integer2;
	
    #region Extensibility Method Definitions
    partial void OnLoaded();
    partial void OnValidate(System.Data.Linq.ChangeAction action);
    partial void OnCreated();
    partial void OnIdChanging(int value);
    partial void OnIdChanged();
    partial void OnInteger1Changing(int value);
    partial void OnInteger1Changed();
    partial void OnInteger4Changing(int value);
    partial void OnInteger4Changed();
    partial void OnInteger3Changing(int value);
    partial void OnInteger3Changed();
    partial void OnInteger5Changing(int value);
    partial void OnInteger5Changed();
    partial void OnInteger2Changing(int value);
    partial void OnInteger2Changed();
    #endregion
	
	public TABLE_WITH_5_COLUMNS()
	{
		OnCreated();
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Storage="_Id", AutoSync=AutoSync.OnInsert, DbType="Int NOT NULL IDENTITY", IsPrimaryKey=true, IsDbGenerated=true)]
	public int Id
	{
		get
		{
			return this._Id;
		}
		set
		{
			if ((this._Id != value))
			{
				this.OnIdChanging(value);
				this.SendPropertyChanging();
				this._Id = value;
				this.SendPropertyChanged("Id");
				this.OnIdChanged();
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
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Name="integer4", Storage="_Integer4", DbType="Int NOT NULL")]
	public int Integer4
	{
		get
		{
			return this._Integer4;
		}
		set
		{
			if ((this._Integer4 != value))
			{
				this.OnInteger4Changing(value);
				this.SendPropertyChanging();
				this._Integer4 = value;
				this.SendPropertyChanged("Integer4");
				this.OnInteger4Changed();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Name="integer3", Storage="_Integer3", DbType="Int NOT NULL")]
	public int Integer3
	{
		get
		{
			return this._Integer3;
		}
		set
		{
			if ((this._Integer3 != value))
			{
				this.OnInteger3Changing(value);
				this.SendPropertyChanging();
				this._Integer3 = value;
				this.SendPropertyChanged("Integer3");
				this.OnInteger3Changed();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Name="integer5", Storage="_Integer5", DbType="Int NOT NULL")]
	public int Integer5
	{
		get
		{
			return this._Integer5;
		}
		set
		{
			if ((this._Integer5 != value))
			{
				this.OnInteger5Changing(value);
				this.SendPropertyChanging();
				this._Integer5 = value;
				this.SendPropertyChanged("Integer5");
				this.OnInteger5Changed();
			}
		}
	}
	
	[global::System.Data.Linq.Mapping.ColumnAttribute(Name="integer2", Storage="_Integer2", DbType="Int NOT NULL")]
	public int Integer2
	{
		get
		{
			return this._Integer2;
		}
		set
		{
			if ((this._Integer2 != value))
			{
				this.OnInteger2Changing(value);
				this.SendPropertyChanging();
				this._Integer2 = value;
				this.SendPropertyChanged("Integer2");
				this.OnInteger2Changed();
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