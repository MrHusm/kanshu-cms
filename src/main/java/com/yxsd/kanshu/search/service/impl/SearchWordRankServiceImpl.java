package com.yxsd.kanshu.search.service.impl;

import com.yxsd.kanshu.base.dao.IBaseDao;
import com.yxsd.kanshu.base.service.impl.BaseServiceImpl;
import com.yxsd.kanshu.search.dao.ISearchWordRankDao;
import com.yxsd.kanshu.search.model.SearchWordRank;
import com.yxsd.kanshu.search.service.ISearchWordRankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service(value = "searchWordRankService")
public class SearchWordRankServiceImpl extends BaseServiceImpl<SearchWordRank, Long> implements ISearchWordRankService {

	@Resource(name = "searchWordRankDao")
	private ISearchWordRankDao searchWordRankDao;

	@Override
	public IBaseDao<SearchWordRank> getBaseDao() {
		return searchWordRankDao;
	}

}
