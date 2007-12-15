/* [{
* (C) Copyright 2007 Nicolas Carranza and individual contributors.
* See the jpen-copyright.txt file in the jpen distribution for a full
* listing of individual contributors.
*
* This file is part of jpen.
*
* jpen is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* jpen is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with jpen.  If not, see <http://www.gnu.org/licenses/>.
* }] */
package jpen.provider;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import jpen.PButton;
import jpen.PButtonEvent;
import jpen.Pen;
import jpen.PenDevice;
import jpen.PenManager;
import jpen.PenProvider;
import jpen.PKind;
import jpen.PLevel;
import jpen.PLevelEvent;

public abstract class AbstractPenDevice
	implements PenDevice {

	private final PenProvider provider;
	private int kindTypeNumber;
	private boolean enabled;

	protected AbstractPenDevice(PenProvider provider) {
		this.provider=provider;
	}

	@Override
	public PenProvider getProvider() {
		return provider;
	}

	@Override
	public int getKindTypeNumber() {
		return kindTypeNumber;
	}

	@Override
	public void setKindTypeNumber(int kindTypeNumber) {
		this.kindTypeNumber=kindTypeNumber;
	}

	@Override
	public boolean isDigitizer() {
		return true;
	}

	@Override
	public boolean getEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled=enabled;
	}

	public final PenManager getPenManager() {
		return provider.getPenManager();
	}

	public final Pen getPen() {
		return getPenManager().pen;
	}

	public final Component getComponent() {
		return getPenManager().component;
	}

	@Override
	public String toString() {
		return "[PenDevice: provider="+getProvider()+", name="+getName()+", kind="+PKind.Type.valueOf(getKindTypeNumber())+"("+getKindTypeNumber()+")]";
	}
}
