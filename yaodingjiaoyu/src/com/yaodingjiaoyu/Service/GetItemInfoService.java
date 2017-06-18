package com.yaodingjiaoyu.Service;

import java.util.List;
import java.util.Map;

public interface GetItemInfoService {
	public Map<String,Integer> getItemInfo(int start_look, int max_look, List<?> list);
}
