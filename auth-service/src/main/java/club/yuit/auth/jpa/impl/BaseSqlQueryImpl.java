package club.yuit.auth.jpa.impl;


import club.yuit.auth.jpa.BaseSqlQuery;
import club.yuit.common.response.PageQueryItems;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @Author yuit
 * @Date: Create in 2018/4/28 14:36
 * @Description
 * @Modified By:
 */
@Repository
@SuppressWarnings("all")
public class BaseSqlQueryImpl implements BaseSqlQuery {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * SQL基础分页查询
     *
     * @param sql 数据查询SQL
     * @param pm 参数
     * @param currentPage 当前页
     * @param pageSize 每页数据量
     * @return
     */
    @Override
    public PageQueryItems baseSqlPageQuery(String sql, List<? extends Object> pm, Integer currentPage, Integer pageSize){

        int cPage = 1;
        int pSize= 30;

        Query query = em.createNativeQuery(sql);

        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        //数据总条数SQL
        StringBuilder countSql =new StringBuilder("SELECT COUNT(*) FROM(%s) result");
        Query countQuery=this.em.createNativeQuery(String.format(countSql.toString(),sql));

        if(pm!=null&&pm.size()>0){

           for(int i = 0; i<pm.size();i++ ){
               query.setParameter(i+1,pm.get(i));
               countQuery.setParameter(i+1,pm.get(i));
           }

        }

        if(pageSize!=null) pSize = pageSize;

        if(currentPage!=null) cPage = currentPage;

        if(pSize!=-1) {
            cPage =(cPage-1)*pSize;
        }

        Long count =  count= Long.parseLong(countQuery.getSingleResult().toString());

        if(pSize>=0){
            query.setFirstResult(cPage);
            query.setMaxResults(pSize);
        }else{
            cPage=-1;
            pSize=-1;
        }





        List<Map<String,Object>> list=query.unwrap(NativeQuery.class).list();

        PageQueryItems items = new PageQueryItems();
        if(pSize==-1){
            items.setCurrentPage(-1);
        }else if(currentPage!=null){
            items.setCurrentPage(currentPage);
        }else {
            items.setCurrentPage(1);
        }

        items.setPageSize(pSize);
        items.setItems(list);
        items.setCount(count);

        return items;
    }

    @Override
    public List<Map<String, Object>> taskQuery(String sql, List<Object> parameters) {

        Query query = em.createNativeQuery(sql);

        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        if(parameters!=null&&parameters.size()>0){

            for(int i = 0; i<parameters.size();i++ ){
                query.setParameter(i+1,parameters.get(i));
            }
        }

        List<Map<String,Object>> items=query.getResultList();

        return items;
    }


}
