package vn.ncuong.web.dwr;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import vn.ncuong.constants.DwrConstants;
import vn.ncuong.db.dao.PostDAO;
import vn.ncuong.db.entity.Post;
import vn.ncuong.web.bean.PostBean;

@RemoteProxy
public class DwrPostServiceImpl implements DwrPostService {

	@Autowired
	private PostDAO postDAO;
	
	@Override
	@RemoteMethod
	public List<PostBean> getPostsInGroup(int position, int numberOfPostInGroup) {
		List<Post> posts = postDAO.findLimitPostWithOrderById(position, numberOfPostInGroup);
		List<PostBean> postBeans = new ArrayList<PostBean>();
		for (Post post : posts) {
			PostBean postBean = new PostBean();
			postBean.setPostId(post.getPostId());
			postBean.setPostTitle(post.getPostTitle());
			postBean.setPostUrl(post.getPostUrl());
			postBean.setPostContent(post.getPostContent());
			postBean.setPostMeta(post.getPostMeta());
			postBean.setCategoryId(post.getCategory().getCategoryIdId());
			postBean.setCategoryName(post.getCategory().getCategoryName());
			postBean.setCategoryUrl(post.getCategory().getCategoryUrl());
			postBean.setCategoryMeta(post.getCategory().getCategoryMeta());
			postBeans.add(postBean);
		}
		
		return postBeans;
	}

	@Override
	@RemoteMethod
	public int getTotalGroup() {
		int result = (int) Math.ceil(postDAO.count()/DwrConstants.PostConstants.ITEMS_PER_GROUP);
		return result;
	}

}
