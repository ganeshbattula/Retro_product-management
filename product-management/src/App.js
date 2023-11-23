import React, { useState, useEffect } from 'react';
import './App.css';
const App = () => {
  const [products, setProducts] = useState([
    {
      id: 1,
      description: 'Apples',
      canExpire: true,
      expiryDate: '2023-12-31',
      category: 'Fruit',
      price: 2.99,
      isSpecial: false
    },
    {
      id: 2,
      description: 'Chicken Breast',
      canExpire: true,
      expiryDate: '2023-11-20',
      category: 'Meat',
      price: 7.99,
      isSpecial: true
    },
    {
      id: 3,
      description: 'Leather Sofa',
      canExpire: false,
      category: 'Furniture',
      price: 499.99,
      isSpecial: false
    },
    {
      id: 4,
      description: 'Organic Lettuce',
      canExpire: true,
      expiryDate: '2023-11-15',
      category: 'Vegetables',
      price: 3.50,
      isSpecial: false
    },
    {
      id: 5,
      description: 'Lamp',
      canExpire: false,
      category: 'Furniture',
      price: 40.00,
      isSpecial: true
    },
  ]);
  const [filter, setFilter] = useState('');
  const [editingProduct, setEditingProduct] = useState(null);

  const getNextProductId = () => {
    const maxId = products.length > 0 ? Math.max(...products.map(p => p.id)) : 0;
    return maxId + 1;
  };
  const getUniqueCategories = () => {
    const categories = products.map(product => product.category);
    return [...new Set(categories)];
  };
  console.log(getUniqueCategories());

  const startEditProduct = (product) => {
    setEditingProduct({ ...product });
  };

  const addNewProduct = () => {
    setEditingProduct({ id: null, description: '', canExpire: false, expiryDate: '', category: '', price: 0, isSpecial: false });
  };
  const handleSaveProduct = (product) => {
    const productExists = products.some(p => p.id === product.id);
  
    if (productExists) {
      const updatedProducts = products.map(p => p.id === product.id ? product : p);
      setProducts(updatedProducts);
    } else {
      const newId = getNextProductId();
      const newProduct = { ...product, id: newId };
      const updatedProducts = [...products, newProduct];
      setProducts(updatedProducts);
    }
  
    setEditingProduct(null);
  };

  const handleCancelEdit = () => {
    setEditingProduct(null);
  };

  const deleteProduct = (id) => {
    setProducts(products.filter(product => product.id !== id));
  };

  const filteredProducts = filter ? products.filter(product => product.category === filter) : products;

  return (
    <div>
      <Filter setFilter={setFilter} />
      <ProductList products={filteredProducts} startEditProduct={startEditProduct} deleteProduct={deleteProduct} />
      <button onClick={addNewProduct}>Add New Product</button>
      {editingProduct && (
        <ProductForm
          initialProduct={editingProduct}
          onSave={handleSaveProduct}
          onCancel={handleCancelEdit}
          getNextProductId={getNextProductId}
          categories={getUniqueCategories()}
        />
      )}
    </div>
  );
};

const Filter = ({ setFilter }) => {
  return (
    <div>
      <label htmlFor="filter">Filter by Category: </label>
      <select id="filter" onChange={(e) => setFilter(e.target.value)}>
        <option value="">All</option>
        <option value="Fruit">Fruit</option>
        <option value="Meat">Meat</option>
        <option value="Furniture">Furniture</option>
        <option value="Vegetables">Vegetables</option>
      </select>
    </div>
  );
};


const ProductList = ({ products, startEditProduct, deleteProduct}) => {
  return (
    <ul>
      {products.map(product => (
        <li key={product.id} style={{ backgroundColor: product.isSpecial ? 'yellow' : 'transparent' }}>
          <p>{product.description}</p>
          <p>Category: {product.category}</p>
          <p>Price: ${product.price}</p>
          {product.canExpire && <p>Expiry Date: {product.expiryDate}</p>}
          <button onClick={() => startEditProduct(product)}>Edit</button>
          <button onClick={() => deleteProduct(product.id)}>Delete</button>
        </li>
      ))}
    </ul>
  );
};

const ProductForm = ({ getNextProductId, initialProduct, onSave, onCancel, categories }) => {
  const [product, setProduct] = useState(initialProduct);

  useEffect(() => {
    setProduct(initialProduct);
  }, [initialProduct]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const productToSave = product.id ? product : { ...product, id: getNextProductId() };
    onSave(productToSave);
    setProduct({ id: null, description: '', canExpire: false, expiryDate: '', category: '', price: 0, isSpecial: false });
  };
  
  return (
    <div className="modal">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Description"
          value={product.description}
          onChange={(e) => setProduct({ ...product, description: e.target.value })}
        />
        <label>
          Can Expire:
          <input
            type="checkbox"
            checked={product.canExpire}
            onChange={(e) => setProduct({ ...product, canExpire: e.target.checked })}
          />
        </label>
        {product.canExpire && (
          <input
            type="date"
            value={product.expiryDate}
            onChange={(e) => setProduct({ ...product, expiryDate: e.target.value })}
          />
        )}
           <label>
          Category:
          <select
            value={product.category}
            onChange={(e) => setProduct({ ...product, category: e.target.value })}
          >
            {categories.map(category => (
              <option key={category} value={category}>{category}</option>
            ))}
          </select>
        </label>
        <input
          type="number"
          placeholder="Price"
          value={product.price}
          onChange={(e) => setProduct({ ...product, price: parseFloat(e.target.value) })}
        />
        <label>
          On Special:
          <input
            type="checkbox"
            checked={product.isSpecial}
            onChange={(e) => setProduct({ ...product, isSpecial: e.target.checked })}
          />
        </label>
        <button type="submit">Save</button>
        <button type="button" onClick={onCancel}>Cancel</button>
        
      </form>
    </div>
  );
};

export default App;