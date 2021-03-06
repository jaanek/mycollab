/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Arrays;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleActivity;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ActivityRelatedItemListComp extends
		RelatedListComp<SimpleActivity, ActivitySearchCriteria> {
	private static final long serialVersionUID = 1L;

	private final boolean allowCreateNew;

	public ActivityRelatedItemListComp(final boolean allowCreateNew) {
		this.allowCreateNew = allowCreateNew;

		initUI();
	}

	private void initUI() {
		if (allowCreateNew) {
			HorizontalLayout buttonLayout = new HorizontalLayout();
			buttonLayout.setSpacing(true);
			final Button newTaskBtn = new Button("New Task",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("task");
						}
					});
			newTaskBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newTaskBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_TASK));
			newTaskBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			buttonLayout.addComponent(newTaskBtn);

			final Button newCallBtn = new Button("New Call",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newCallBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newCallBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_CALL));
			newCallBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			buttonLayout.addComponent(newCallBtn);

			final Button newMeetingBtn = new Button("New Meeting",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							fireNewRelatedItem("call");
						}
					});
			newMeetingBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			newMeetingBtn.setEnabled(AppContext
					.canWrite(RolePermissionCollections.CRM_MEETING));
			newMeetingBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			buttonLayout.addComponent(newMeetingBtn);

			this.addComponent(buttonLayout);
		}

		tableItem = new ActivityTableDisplay(Arrays.asList(
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
						"subject", UIConstants.TABLE_EX_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						"status", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
						"eventType", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
						"startDate", UIConstants.TABLE_DATE_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER),
						"endDate", UIConstants.TABLE_DATE_WIDTH)));

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleActivity simpleEvent = (SimpleActivity) event
								.getData();
						if ("Task".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this,
											simpleEvent.getId()));
						} else if ("Meeting".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingRead(this,
											simpleEvent.getId()));
						} else if ("Call".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this,
											simpleEvent.getId()));
						}
					}
				});

		this.addComponent(tableItem);
	}

	@Override
	public void refresh() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
