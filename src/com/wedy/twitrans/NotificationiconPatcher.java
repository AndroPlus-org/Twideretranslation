package com.wedy.twitrans;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class NotificationiconPatcher implements IXposedHookZygoteInit, IXposedHookInitPackageResources{

	private static final String PACKAGE_ESF = "org.mariotaku.twidere";
	private static String modulePath = null;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modulePath = startupParam.modulePath;
	}



	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		if(!resparam.packageName.equals(PACKAGE_ESF)){
			return;
		}

		XModuleResources modRes = XModuleResources.createInstance(modulePath, resparam.res);
resparam.res.setReplacement(PACKAGE_ESF, "string", "empty_account_selection_disallowed", modRes.fwd(R.string.empty_account_selection_disallowed));
resparam.res.setReplacement(PACKAGE_ESF, "string", "multi_select", modRes.fwd(R.string.multi_select));
resparam.res.setReplacement(PACKAGE_ESF, "string", "long_click_to_open_menu", modRes.fwd(R.string.long_click_to_open_menu));


	}

}
