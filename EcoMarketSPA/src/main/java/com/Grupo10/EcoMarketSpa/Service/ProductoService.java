package com.Grupo10.EcoMarketSpa.Service;

import com.Grupo10.EcoMarketSpa.Model.Producto;
import com.Grupo10.EcoMarketSpa.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    //Listar Producto
    public String getAllProducto(){
        String info = "";
        for(Producto producto : productoRepository.findAll()){
            info+="Id Producto: "+producto.getIdProducto()+"\n";
            info+="Nombre del Producto: "+producto.getNombreProducto()+"\n";
            info+="Descripcion del Producto: "+producto.getDescripcionProducto()+"\n";
            info+="Precio del Producto: "+producto.getPrecioProducto()+"\n";
            info+="Stock del Producto: "+producto.getStockProducto()+"\n";
            info+="Categoria del Producto: "+producto.getCategoriaProducto()+"\n";
        }
        if (info.isEmpty()){
            return "No existe el producto";
        }else{
            return info;
        }
    }
    //Buscar Producto por Id
    public String getProductoById(int id){
        String info = "";
        if (productoRepository.existsById(id)){
            Producto producto = productoRepository.findById(id).get();
            info+="Id Producto: "+producto.getIdProducto()+"\n";
            info+="Nombre del Producto: "+producto.getNombreProducto()+"\n";
            info+="Descripcion del Producto: "+producto.getDescripcionProducto()+"\n";
            info+="Precio del Producto: "+producto.getPrecioProducto()+"\n";
            info+="Stock del Producto: "+producto.getStockProducto()+"\n";
            info+="Categoria del Producto: "+producto.getCategoriaProducto()+"\n";
            return info;
        }else {
            return "No existe el producto";
        }
    }
    //Agregar Producto
    public String addProducto(Producto producto){
        if (!productoRepository.existsById(producto.getIdProducto())){
            productoRepository.save(producto);
            return "El producto se agrego correctamente";
        }else{
            return "El producto ya existe";
        }
    }
    //Eliminar Producto
    public String deleteProducto(int id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return "El producto se elimino correctamente";
        }else{
            return "El producto no existe";
        }
    }
    //Actualizar producto
    public String updateProducto(int id,Producto producto){
        if (productoRepository.existsById(id)){
            Producto productoUpdate = productoRepository.findById(id).get();
            productoUpdate.setNombreProducto(producto.getNombreProducto());
            productoUpdate.setDescripcionProducto(producto.getDescripcionProducto());
            productoUpdate.setPrecioProducto(producto.getPrecioProducto());
            productoUpdate.setStockProducto(producto.getStockProducto());
            productoUpdate.setCategoriaProducto(producto.getCategoriaProducto());
            productoRepository.save(productoUpdate);
            return "Producto actualizado correctamente";
        }else{
            return "Producto no existe";
        }
    }
}
