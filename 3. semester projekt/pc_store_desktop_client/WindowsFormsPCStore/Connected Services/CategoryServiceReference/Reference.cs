﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WindowsFormsPCStore.CategoryServiceReference {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Category", Namespace="http://schemas.datacontract.org/2004/07/Domain")]
    [System.SerializableAttribute()]
    public partial class Category : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int _idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private WindowsFormsPCStore.CategoryServiceReference.Product[] _productsField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string _typeField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int _id {
            get {
                return this._idField;
            }
            set {
                if ((this._idField.Equals(value) != true)) {
                    this._idField = value;
                    this.RaisePropertyChanged("_id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public WindowsFormsPCStore.CategoryServiceReference.Product[] _products {
            get {
                return this._productsField;
            }
            set {
                if ((object.ReferenceEquals(this._productsField, value) != true)) {
                    this._productsField = value;
                    this.RaisePropertyChanged("_products");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string _type {
            get {
                return this._typeField;
            }
            set {
                if ((object.ReferenceEquals(this._typeField, value) != true)) {
                    this._typeField = value;
                    this.RaisePropertyChanged("_type");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Product", Namespace="http://schemas.datacontract.org/2004/07/Domain")]
    [System.SerializableAttribute()]
    public partial class Product : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int _categoryIdField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string _descriptionField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int _idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string _nameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private decimal _priceField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int _supplierIdField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int _categoryId {
            get {
                return this._categoryIdField;
            }
            set {
                if ((this._categoryIdField.Equals(value) != true)) {
                    this._categoryIdField = value;
                    this.RaisePropertyChanged("_categoryId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string _description {
            get {
                return this._descriptionField;
            }
            set {
                if ((object.ReferenceEquals(this._descriptionField, value) != true)) {
                    this._descriptionField = value;
                    this.RaisePropertyChanged("_description");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int _id {
            get {
                return this._idField;
            }
            set {
                if ((this._idField.Equals(value) != true)) {
                    this._idField = value;
                    this.RaisePropertyChanged("_id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string _name {
            get {
                return this._nameField;
            }
            set {
                if ((object.ReferenceEquals(this._nameField, value) != true)) {
                    this._nameField = value;
                    this.RaisePropertyChanged("_name");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public decimal _price {
            get {
                return this._priceField;
            }
            set {
                if ((this._priceField.Equals(value) != true)) {
                    this._priceField = value;
                    this.RaisePropertyChanged("_price");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int _supplierId {
            get {
                return this._supplierIdField;
            }
            set {
                if ((this._supplierIdField.Equals(value) != true)) {
                    this._supplierIdField = value;
                    this.RaisePropertyChanged("_supplierId");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="CategoryServiceReference.ICategoryService")]
    public interface ICategoryService {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICategoryService/Read", ReplyAction="http://tempuri.org/ICategoryService/ReadResponse")]
        WindowsFormsPCStore.CategoryServiceReference.Category Read(string type);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICategoryService/Read", ReplyAction="http://tempuri.org/ICategoryService/ReadResponse")]
        System.Threading.Tasks.Task<WindowsFormsPCStore.CategoryServiceReference.Category> ReadAsync(string type);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICategoryService/GetAll", ReplyAction="http://tempuri.org/ICategoryService/GetAllResponse")]
        WindowsFormsPCStore.CategoryServiceReference.Category[] GetAll();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICategoryService/GetAll", ReplyAction="http://tempuri.org/ICategoryService/GetAllResponse")]
        System.Threading.Tasks.Task<WindowsFormsPCStore.CategoryServiceReference.Category[]> GetAllAsync();
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ICategoryServiceChannel : WindowsFormsPCStore.CategoryServiceReference.ICategoryService, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class CategoryServiceClient : System.ServiceModel.ClientBase<WindowsFormsPCStore.CategoryServiceReference.ICategoryService>, WindowsFormsPCStore.CategoryServiceReference.ICategoryService {
        
        public CategoryServiceClient() {
        }
        
        public CategoryServiceClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public CategoryServiceClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CategoryServiceClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CategoryServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public WindowsFormsPCStore.CategoryServiceReference.Category Read(string type) {
            return base.Channel.Read(type);
        }
        
        public System.Threading.Tasks.Task<WindowsFormsPCStore.CategoryServiceReference.Category> ReadAsync(string type) {
            return base.Channel.ReadAsync(type);
        }
        
        public WindowsFormsPCStore.CategoryServiceReference.Category[] GetAll() {
            return base.Channel.GetAll();
        }
        
        public System.Threading.Tasks.Task<WindowsFormsPCStore.CategoryServiceReference.Category[]> GetAllAsync() {
            return base.Channel.GetAllAsync();
        }
    }
}