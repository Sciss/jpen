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

import java.applet.Applet;
import java.awt.Component;
import java.awt.geom.Point2D;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.util.List;
import java.util.ResourceBundle;

public final class Utils {
	private static ResourceBundle moduleProperties;

	public static final void getLocationOnScreen(Component c, Point2D.Float location) {
		location.x=location.y=0;
		for(Component parentComponent=c; parentComponent!=null; parentComponent=parentComponent.getParent()) {
			if(parentComponent instanceof Applet) {
				try {
					Point p=parentComponent.getLocationOnScreen();
					location.x+=p.x;
					location.y+=p.y;
				} catch(IllegalComponentStateException ex) {}
				return;
			}
			location.x+=parentComponent.getX();
			location.y+=parentComponent.getY();
			if(parentComponent instanceof Window)
				return;
		}
	}

	private static final ResourceBundle getModuleProperties() {
		if(moduleProperties==null) {
			moduleProperties=ResourceBundle.getBundle("jpen.module");
		}
		return moduleProperties;
	}

	private static final String getJniLibName() {
		return getModuleProperties().getString("module.id")+"-"+
		       getModuleProperties().getString("module.version");
	}

	public static final void loadLibrary() {
		System.loadLibrary(getJniLibName());
	}

	public static final void loadLibraryOrFail() {
		try {
			System.loadLibrary(getJniLibName());
		} catch(Exception ex) {
			throw new AssertionError(ex);
		}
	}
}