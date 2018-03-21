package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Product;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product>implements ProductDao{
    public Product findByPdId(int productId){
        return getByKey(productId);
    }
    public Product findByName(String name){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Product) crit.uniqueResult();
    }
/*
    public Product addProduct(Product product) {
        persist(product);
        return product;
    }
*/
    public void updateProduct(Product product) {
        update(product);
    }

    public List findByCategory(String ctg){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("category", ctg));
        return  crit.list();
    }

    public List findBySubCategory(String ctg, String stgc){
/*        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("category", ctg));
        Query query = getSession().createSQLQuery("SELECT * FROM ashishShopping.PRODUCTS_DETAILS where productId IN (Select Product_productId from Product_subcategory where subCategory = '"+stgc+"');");
        List list = query.list();
        return list;*/
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("subcategory", stgc));
        crit.add(Restrictions.eq("category", ctg));
        return crit.list();
    }

    /*
    * Query query = getSession().createSQLQuery("SELECT * FROM HibExample.PRODUCTS where productId IN (Select Product_productId from Product_subCategory where subCategory = '"+subCategory+"');");List list = query.list();return list;
    * */
    public List<Product> findByBrand(String brand){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("brand", brand));
        return crit.list();
    }

    public List<Product> listAllProducts(){
        Criteria crit = createEntityCriteria();
//        crit.addOrder(Order.asc("type"));
        return crit.list();
    }
    public List getProductsBySearchString(String searchString) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.or
                (Restrictions.like("pdName", searchString, MatchMode.ANYWHERE),
                Restrictions.like("details",searchString,MatchMode.ANYWHERE),
                Restrictions.like("category",searchString,MatchMode.ANYWHERE),
                Restrictions.like("subcategory",searchString,MatchMode.ANYWHERE)));
        return criteria.list();
    }
    @Override
    public Product save(Product p) {
        persist(p);
        return p;
    }
    public List<Product> findByPrice(String category, double price1, double price2){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("category", category));
        crit.add(Restrictions.ge("price", price1));
        crit.add(Restrictions.lt("price", price2));
        crit.setResultTransformer(crit.DISTINCT_ROOT_ENTITY);
        return (List<Product>) crit.list();
    }
}



