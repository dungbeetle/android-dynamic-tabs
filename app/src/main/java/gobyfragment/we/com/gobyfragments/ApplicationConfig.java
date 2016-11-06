package gobyfragment.we.com.gobyfragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hekeji on 16/11/5.
 */
public class ApplicationConfig implements Serializable {

    private static ApplicationConfig applicationConfig = null;

    private ApplicationConfig(){

    }

    public static ApplicationConfig getInstance(){
        if(applicationConfig == null){
            applicationConfig = new ApplicationConfig();
        }
        return applicationConfig;
    }

    public static void init(String configString){
        if(applicationConfig == null){
            applicationConfig = new ApplicationConfig();
        }

        try {
            JSONObject jsonObject = new JSONObject(configString);
            applicationConfig.setVersion(jsonObject.getString("config_verson"));
            applicationConfig.setNavBarColor(getColorModel(jsonObject.getJSONObject("navbar_color")));
            applicationConfig.setNavbarTextColor(getColorModel(jsonObject.getJSONObject("navbartext_color")));
            applicationConfig.setBackBtnColor(getColorModel(jsonObject.getJSONObject("backbtn_color")));
            applicationConfig.setTabColor(getColorModel(jsonObject.getJSONObject("tabbar_color")));

            JSONArray tabArray = jsonObject.getJSONArray("tab_config");
            for(int i = 0; i < tabArray.length(); i++){
                applicationConfig.getPageModelMap().put(i + 1, getPageModel(tabArray.getJSONObject(i)));
//                if(i == 0){
////                    applicationConfig.getPageModelMap().put("firstTab",getPageModel(tabArray.getJSONObject(i)));
//
//                }
//                if(i == 1){
//                    applicationConfig.getPageModelMap().put("secondTab",getPageModel(tabArray.getJSONObject(i)));
//                    applicationConfig.getPageModelMap().put("secondTab",getPageModel(tabArray.getJSONObject(i)));
//                }
//                if(i == 2){
//                    applicationConfig.getPageModelMap().put("thirdTab",getPageModel(tabArray.getJSONObject(i)));
//                    applicationConfig.getPageModelMap().put("thirdTab",getPageModel(tabArray.getJSONObject(i)));
//                }
//                if(i == 3){
//                    applicationConfig.getPageModelMap().put("fourthTab",getPageModel(tabArray.getJSONObject(i)));
//                    applicationConfig.getPageModelMap().put("fourthTab",getPageModel(tabArray.getJSONObject(i)));
//                }
//                if(i == 4){
//                    applicationConfig.getPageModelMap().put("fifthTab",getPageModel(tabArray.getJSONObject(i)));
//                    applicationConfig.getPageModelMap().put("fifthTab",getPageModel(tabArray.getJSONObject(i)));
//                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private static PageModel getPageModel(JSONObject jsonObject) throws JSONException{
        PageModel pageModel = new PageModel();
        pageModel.setNomal(jsonObject.getString("nomal"));
        pageModel.setContent(jsonObject.getString("content"));
        pageModel.setSelect(jsonObject.getString("select"));
        if(jsonObject.getString("tabbar_selected") != null && jsonObject.getString("tabbar_selected").equalsIgnoreCase("1")){
            pageModel.setSelected(Boolean.TRUE);
        }
        return pageModel;
    }

    private static ColorModel getColorModel(JSONObject jsonObject) throws JSONException {
        ColorModel colorModel = new ColorModel();
        colorModel.setR(jsonObject.getString("R"));
        colorModel.setG(jsonObject.getString("G"));
        colorModel.setB(jsonObject.getString("B"));
        return colorModel;
    }

    private String version;
    private ColorModel navBarColor;
    private ColorModel navbarTextColor;
    private ColorModel backBtnColor;
    private ColorModel tabColor;

    private Map<Integer, PageModel> pageModelMap = new HashMap<>();


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ColorModel getNavBarColor() {
        return navBarColor;
    }

    public void setNavBarColor(ColorModel navBarColor) {
        this.navBarColor = navBarColor;
    }

    public ColorModel getNavbarTextColor() {
        return navbarTextColor;
    }

    public void setNavbarTextColor(ColorModel navbarTextColor) {
        this.navbarTextColor = navbarTextColor;
    }

    public ColorModel getBackBtnColor() {
        return backBtnColor;
    }

    public void setBackBtnColor(ColorModel backBtnColor) {
        this.backBtnColor = backBtnColor;
    }

    public ColorModel getTabColor() {
        return tabColor;
    }

    public void setTabColor(ColorModel tabColor) {
        this.tabColor = tabColor;
    }

    public Map<Integer, PageModel> getPageModelMap() {
        return pageModelMap;
    }

    public void setPageModelMap(Map<Integer, PageModel> pageModelMap) {
        this.pageModelMap = pageModelMap;
    }
}

