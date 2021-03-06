package com.esofthead.mycollab.mobile.module.crm.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.crm.events.AccountEvent;
import com.esofthead.mycollab.mobile.module.user.ui.UserPanel;
import com.esofthead.mycollab.mobile.ui.AbstractNavigationMenu;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class CrmNavigationMenu extends AbstractNavigationMenu {
	private static final long serialVersionUID = 1L;

	private final Map<String, MenuButton> buttonMap = new HashMap<String, MenuButton>();

	public CrmNavigationMenu() {
		super();

		setWidth("100%");

		UserPanel userPanel = new UserPanel();
		userPanel.setWidth("100%");
		addComponent(userPanel);

		final MenuButton accountBtn = new MenuButton("Accounts", "&#xE601;");
		addComponent(accountBtn);
		buttonMap.put("Accounts", accountBtn);

		final MenuButton contactBtn = new MenuButton("Contacts", "&#xE603;");
		addComponent(contactBtn);
		buttonMap.put("Contacts", contactBtn);

		final MenuButton campaignBtn = new MenuButton("Campaigns", "&#xE602;");
		addComponent(campaignBtn);
		buttonMap.put("Campaign", campaignBtn);

		final MenuButton leadBtn = new MenuButton("Leads", "&#xE609;");
		addComponent(leadBtn);
		buttonMap.put("Leads", leadBtn);

		final MenuButton opportunityBtn = new MenuButton("Opportunities",
				"&#xE604;");
		addComponent(opportunityBtn);
		buttonMap.put("Opportunities", opportunityBtn);

		final MenuButton caseBtn = new MenuButton("Cases", "&#xE605;");
		addComponent(caseBtn);
		buttonMap.put("Cases", caseBtn);

		final MenuButton activityBtn = new MenuButton("Activities", "&#xE606;");
		addComponent(activityBtn);
		buttonMap.put("Activities", activityBtn);

		final MenuButton documentBtn = new MenuButton("Documents", "&#xE607;");
		addComponent(documentBtn);
		buttonMap.put("Documents", documentBtn);

		final MenuButton settingBtn = new MenuButton("Settings", "&#xE608;");
		addComponent(settingBtn);
		buttonMap.put("Settings", settingBtn);
	}

	@Override
	protected Button.ClickListener createDefaultButtonClickListener() {

		return new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final String caption = ((MenuButton) event.getButton())
						.getBtnId();

				if ("Accounts".equals(caption)) {
					EventBus.getInstance().fireEvent(
							new AccountEvent.GotoList(this, null));
				}

				for (final Iterator<MenuButton> it = CrmNavigationMenu.this
						.buttonIterator(); it.hasNext();) {
					final MenuButton btn = it.next();
					btn.removeStyleName("isSelected");
				}

				event.getButton().addStyleName("isSelected");
			}
		};
	}

	public Iterator<MenuButton> buttonIterator() {
		return buttonMap.values().iterator();
	}
}
