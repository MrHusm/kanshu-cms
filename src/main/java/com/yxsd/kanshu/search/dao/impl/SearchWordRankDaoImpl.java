package com.yxsd.kanshu.search.dao.impl;

import com.yxsd.kanshu.base.dao.impl.BaseDaoImpl;
import com.yxsd.kanshu.search.dao.ISearchWordRankDao;
import com.yxsd.kanshu.search.model.SearchWordRank;
import org.springframework.stereotype.Repository;


@Repository(value="searchWordRankDao")
public class SearchWordRankDaoImpl extends BaseDaoImpl<SearchWordRank> implements ISearchWordRankDao {
}
