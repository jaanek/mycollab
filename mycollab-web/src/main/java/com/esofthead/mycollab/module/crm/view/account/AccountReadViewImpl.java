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
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class AccountReadViewImpl extends AbstractPageView implements
		AccountReadView {

	private static final long serialVersionUID = 1L;
	private AccountReadComp accountPreview;

	public AccountReadViewImpl() {
		super();
		accountPreview = new AccountReadComp();
		this.addComponent(accountPreview);
	}

	@Override
	public void previewItem(SimpleAccount item) {
		accountPreview.previewItem(item);
	}

	@Override
	public HasPreviewFormHandlers<SimpleAccount> getPreviewFormHandlers() {
		return accountPreview.getPreviewForm();
	}

	@Override
	public SimpleAccount getItem() {
		return accountPreview.getBeanItem();
	}

	@Override
	public IRelatedListHandlers<SimpleContact> getRelatedContactHandlers() {
		return accountPreview.getAssociateContactList();
	}

	@Override
	public IRelatedListHandlers getRelatedOpportunityHandlers() {
		return accountPreview.getAssociateOpportunityList();
	}

	@Override
	public IRelatedListHandlers getRelatedLeadHandlers() {
		return accountPreview.getAssociateLeadList();
	}

	@Override
	public IRelatedListHandlers getRelatedCaseHandlers() {
		return accountPreview.getAssociateCaseList();
	}

	@Override
	public IRelatedListHandlers getRelatedActivityHandlers() {
		return accountPreview.getAssociateActivityList();
	}
}
