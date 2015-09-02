package vn.ncuong.web.dwr;

import java.util.List;

import vn.ncuong.web.bean.PostBean;

public interface DwrPostService {

	public List<PostBean> getPostsInGroup(int position, int numberOfPostInGroup);

	public int getTotalGroup();
}
