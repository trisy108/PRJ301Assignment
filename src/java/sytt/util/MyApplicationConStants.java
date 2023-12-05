/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.util;

/**
 *
 * @author Admin
 */
public class MyApplicationConStants {
    public class DispatchFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
        public static final String UPDATE_ACCOUNT_CONTRLLER = "updateController";
        public static final String LOG_OUT = "logoutController";
        public static final String ADD_TO_CART = "addCartController";
        public static final String VIEW_PAGE = "viewPage";
        public static final String REMOVE_CART = "removeController";
        public static final String SEARCH_PRODUCT = "searchProductController";
        public static final String CREATE_ACCOUNT = "createAccountController";
        public static final String START_UP_CONTROLLER = "startupController";
        public static final String CHECK_OUT_CONTROLLER = "checkoutController";
    }

    public class LoginFeatures {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String INVALID_PAGE = "invalidPage";
    }
    public class AddToCartFeature{
        public static final String SHOPPING_PAGE="searchProductPage";
    }
    public class CheckOutFeature{
        public static final String VIEW_PAGE="viewPage";
        public static final String RESULT_PAGE="checkoutsuccess";
    }    
    public class DeleteAcountFeature{
        public static final String ERROR_PAGE="errorPage";
    }
    public class LogoutFeature{
        public static final String LOGIN_PAGE="";
    }
    public class SearchProductFeature{
        public static final String SEARCH_PRODUCT_PAGE="searchProductPage";
    }
    public class SearchLastNameFeature{
        public static final String SEARCH_PAGE="searchPage";
    }
    public class UpdateAccountFeature{
        public static final String ERROR_PAGE = "errorPage";
    }
    public class CreateAccountFeature{
        public static final String RESULT_PAGE="createNewAccountPage";
        public static final String LOGIN_PAGE="";
    }
}
