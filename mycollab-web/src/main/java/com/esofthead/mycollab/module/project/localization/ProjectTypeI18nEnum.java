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
package com.esofthead.mycollab.module.project.localization;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/project/type")
@LocaleData({ @Locale("en_US") })
public enum ProjectTypeI18nEnum {
	PROJECT_ITEM, MESSAGE_ITEM, PHASE_ITEM, TASK_ITEM, TASKGROUP_ITEM, BUG_ITEM, COMPONENT_ITEM, VERSION_ITEM, RISK_ITEM, PROBLEM_ITEM, STANDUP_ITEM
}
