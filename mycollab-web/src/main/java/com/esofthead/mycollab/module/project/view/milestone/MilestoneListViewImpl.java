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

package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractPageView implements
		MilestoneListView {
	private static final long serialVersionUID = 1L;

	private final VerticalLayout inProgressContainer;

	private final VerticalLayout futureContainer;

	private final VerticalLayout closeContainer;
	private final Button createBtn;

	public MilestoneListViewImpl() {
		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName("milestonelist-header");
		final HorizontalLayout header = new HorizontalLayout();
		final Label titleLbl = new Label("Phases");
		titleLbl.addStyleName("h2");
		header.setWidth("100%");
		final Image icon = new Image(null,
				MyCollabResource.newResource("icons/24/project/phase.png"));
		header.addComponent(icon);
		header.setComponentAlignment(icon, Alignment.MIDDLE_LEFT);
		header.addComponent(titleLbl);
		header.setComponentAlignment(titleLbl, Alignment.MIDDLE_LEFT);
		header.setExpandRatio(titleLbl, 1.0f);
		header.setSpacing(true);

		this.createBtn = new Button(
				LocalizationHelper.getMessage(TaskI18nEnum.NEW_PHASE_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoAdd(
										MilestoneListViewImpl.this, null));
					}
				});
		this.createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		this.createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(this.createBtn);
		header.setComponentAlignment(this.createBtn, Alignment.MIDDLE_RIGHT);
		headerWrapper.addComponent(header);
		this.addComponent(headerWrapper);

		final CustomLayout bodyContent = CustomLayoutLoader
				.createLayout("milestoneView");
		bodyContent.setWidth("100%");
		bodyContent.setStyleName("milestone-view");

		final HorizontalLayout closedHeaderLayout = new HorizontalLayout();
		closedHeaderLayout.setSpacing(true);
		final Image embeddClosed = new Image(null,
				MyCollabResource
						.newResource("icons/16/project/phase_closed.png"));
		closedHeaderLayout.addComponent(embeddClosed);
		closedHeaderLayout.setComponentAlignment(embeddClosed,
				Alignment.MIDDLE_CENTER);
		final Label closedHeader = new Label("Closed");
		closedHeader.setSizeUndefined();
		closedHeaderLayout.addComponent(closedHeader);
		closedHeaderLayout.setComponentAlignment(closedHeader,
				Alignment.MIDDLE_CENTER);

		bodyContent.addComponent(closedHeaderLayout, "closed-header");
		this.closeContainer = new VerticalLayout();
		this.closeContainer.setWidth("100%");
		bodyContent.addComponent(this.closeContainer, "closed-milestones");

		final HorizontalLayout inProgressHeaderLayout = new HorizontalLayout();
		inProgressHeaderLayout.setSpacing(true);
		final Image embeddInProgress = new Image(null,
				MyCollabResource
						.newResource("icons/16/project/phase_progress.png"));
		inProgressHeaderLayout.addComponent(embeddInProgress);
		inProgressHeaderLayout.setComponentAlignment(embeddInProgress,
				Alignment.MIDDLE_CENTER);
		final Label inProgressHeader = new Label("In Progress");
		inProgressHeader.setSizeUndefined();
		inProgressHeaderLayout.addComponent(inProgressHeader);
		inProgressHeaderLayout.setComponentAlignment(inProgressHeader,
				Alignment.MIDDLE_CENTER);

		bodyContent.addComponent(inProgressHeaderLayout, "in-progress-header");
		this.inProgressContainer = new VerticalLayout();
		this.inProgressContainer.setWidth("100%");
		bodyContent.addComponent(this.inProgressContainer,
				"in-progress-milestones");

		final HorizontalLayout futureHeaderLayout = new HorizontalLayout();
		futureHeaderLayout.setSpacing(true);
		final Image embeddFuture = new Image(null,
				MyCollabResource
						.newResource("icons/16/project/phase_future.png"));
		futureHeaderLayout.addComponent(embeddFuture);
		futureHeaderLayout.setComponentAlignment(embeddFuture,
				Alignment.MIDDLE_CENTER);
		final Label futureHeader = new Label("Future");
		futureHeader.setSizeUndefined();
		futureHeaderLayout.addComponent(futureHeader);
		futureHeaderLayout.setComponentAlignment(futureHeader,
				Alignment.MIDDLE_CENTER);

		bodyContent.addComponent(futureHeaderLayout, "future-header");
		this.futureContainer = new VerticalLayout();
		this.futureContainer.setWidth("100%");
		bodyContent.addComponent(this.futureContainer, "future-milestones");

		this.addComponent(bodyContent);
		this.setExpandRatio(bodyContent, 1.0f);
	}

	private ComponentContainer constructMilestoneBox(
			final SimpleMilestone milestone) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName(UIConstants.MILESTONE_BOX);
		layout.setWidth("100%");

		final Button milestoneLink = new Button(milestone.getName(),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoRead(
										MilestoneListViewImpl.this, milestone
												.getId()));
					}
				});
		milestoneLink.setStyleName("link");
		milestoneLink.addStyleName("bold");
		milestoneLink.addStyleName(UIConstants.WORD_WRAP);
		milestoneLink.setWidth("100%");
		milestone.setDescription(milestone.getDescription());

		layout.addComponent(milestoneLink);

		HorizontalLayout spacing = new HorizontalLayout();
		spacing.setHeight("8px");
		spacing.setWidth("100%");
		layout.addComponent(spacing);

		final GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1,
				5, "100%", "80px");
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getStartdate(),
						"<<Not Set>>")), "Start Date", 0, 0,
				Alignment.MIDDLE_LEFT);
		layoutHelper.addComponent(
				new Label(AppContext.formatDate(milestone.getEnddate(),
						"<<Not Set>>")), "End Date", 0, 1,
				Alignment.MIDDLE_LEFT);

		CssLayout linkWrapper = new CssLayout();
		linkWrapper.setWidth("100%");
		linkWrapper.addComponent(new ProjectUserLink(milestone.getOwner(),
				milestone.getOwnerAvatarId(), milestone.getOwnerFullName(),
				false, true));
		layoutHelper.addComponent(linkWrapper, LocalizationHelper
				.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 2,
				Alignment.MIDDLE_LEFT);

		final ProgressBarIndicator progressTask = new ProgressBarIndicator(
				milestone.getNumTasks(), milestone.getNumOpenTasks());
		progressTask.setWidth("100%");

		layoutHelper.addComponent(progressTask, "Tasks", 0, 3,
				Alignment.MIDDLE_LEFT);

		final ProgressBarIndicator progressBug = new ProgressBarIndicator(
				milestone.getNumBugs(), milestone.getNumOpenBugs());
		progressBug.setWidth("100%");

		layoutHelper.addComponent(progressBug, "Bugs", 0, 4,
				Alignment.MIDDLE_LEFT);
		final GridLayout milestoneInfoLayout = layoutHelper.getLayout();
		milestoneInfoLayout.setWidth("100%");
		milestoneInfoLayout.setMargin(false);
		milestoneInfoLayout.setSpacing(true);
		layout.addComponent(milestoneInfoLayout);

		return layout;
	}

	@Override
	public void displayMilestones(final List<SimpleMilestone> milestones) {
		this.createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.MILESTONES));

		this.inProgressContainer.removeAllComponents();
		this.futureContainer.removeAllComponents();
		this.closeContainer.removeAllComponents();

		for (final SimpleMilestone milestone : milestones) {
			if (SimpleMilestone.STATUS_INPROGRESS.equals(milestone.getStatus())) {
				this.inProgressContainer.addComponent(this
						.constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_FUTURE.equals(milestone
					.getStatus())) {
				this.futureContainer.addComponent(this
						.constructMilestoneBox(milestone));
			} else if (SimpleMilestone.STATUS_CLOSE.equals(milestone
					.getStatus())) {
				this.closeContainer.addComponent(this
						.constructMilestoneBox(milestone));
			}
		}

	}
}
