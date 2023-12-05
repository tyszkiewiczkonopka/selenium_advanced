package providers;

public class UrlProvider {
    public static final String APP = "http://146.59.32.4/index.php";
    public static final String SIGN_IN = APP + "?controller=authentication&create_account=1";
    public static final String CATEGORY_CLOTHES = APP + "?id_category=3&controller=category&id_lang=2";
    public static final String CATEGORY_ACCESSORIES = APP + "?id_category=6&controller=category&id_lang=2";
    public static final String CATEGORY_ART = APP + "?id_category=9&controller=category&id_lang=2";
    public static final String CART = APP + "?controller=cart&action=show";
    public static final String LOGIN = APP + "?controller=authentication&back=my-account";



}
