package vn.ncuong.cache;

public interface CacheConstants {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public final static String FIELD_SEPARATOR = "@@";
	public final static String SAVE = "SAVE";
	public final static String SAVE_OR_UPDATE = "SAVE_OR_UPDATE";
	public final static String UPDATE = "UPDATE";
	public final static String DELETE = "DELETE";

	public interface SiteGlobal {
		public final static String SITE_GLOBAL_CACHE = "site.global.cache";
	}

	public interface CategoryDAO {
		public final static String FIND_ALL_CATEGORY = "CategoryDAOImpl.findAllCategoryWithCache.cache";
	}
	
	public interface PostDAO {
		public final static String FIND_ALL_POST = "PostDAOImpl.findAllPostWithCache.cache";
		public final static String FIND_LIMIT_POST_ORDER_BY_ID_WITHCACHE = "PostDAOImpl.findLimitPostOrderByIdWithCache.cache";
	}
	public interface MailUtil {
		public final static String GET_MAIL_SENDER_CACHE = "MailUtil.getMailSender.cache";
	}
}
