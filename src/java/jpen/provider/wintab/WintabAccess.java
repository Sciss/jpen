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
package jpen.provider.wintab;

import jpen.PLevel;
import jpen.provider.Utils;

class WintabAccess {
	static{
		Utils.loadLibraryOrFail();
	}
	/**
	This must be like E_csrTypes enumeration.
	*/
	public enum CursorType{UNDEF, PENTIP, PUCK, PENERASER}

	private final int cellIndex;

	WintabAccess() throws Exception {
		this.cellIndex=create();
		if(cellIndex==-1)
			throw new Exception(getError());
	}

	private static native int create();
	private static native String getError();

	int getValue(PLevel.Type levelType) {
		return getValue(cellIndex, levelType.ordinal());
	}

	private static native int getValue(int cellIndex, int levelTypeOrdinal);

	public boolean nextPacket() {
		return nextPacket(cellIndex);
	}

	private static native boolean nextPacket(int cellIndex);


	public boolean getEnabled() {
		return getEnabled(cellIndex);
	}

	private static native boolean getEnabled(int cellIndex);

	public void setEnabled(boolean enabled) {
		setEnabled(cellIndex, enabled);
	}

	private static native void setEnabled(int cellIndex, boolean enabled);

	public PLevel.Range getLevelRange(PLevel.Type levelType) {
		int[] minMax=getLevelRange(cellIndex, levelType.ordinal());
		return new PLevel.Range(minMax[0], minMax[1]);
	}

	private static native int[] getLevelRange(int cellIndex, int levelTypeOrdinal);

	public int getCursor() {
		return getCursor(cellIndex);
	}

	private static native int getCursor(int cellIndex);

	public int getButtons() {
		return getButtons(cellIndex);
	}

	private static native int getButtons(int cellIndex);

	public static CursorType getCursorType(int cursor) {
		return CursorType.values()[getCursorTypeOrdinal(cursor)];
	}

	private static native int getCursorTypeOrdinal(int cursor);

	public int getFirstCursor() {
		return getFirstCursor(cellIndex);
	}

	private static native int getFirstCursor(int cellIndex);

	public int getCursorsCount() {
		return getCursorsCount(cellIndex);
	}

	private static native int getCursorsCount(int cellIndex);

	public static native boolean getCursorActive(int cursor);

	public static native String getCursorName(int cursor);

	public static native long getCursorId(int cursor);

	public String getDeviceName() {
		return getDeviceName(cellIndex);
	}

	private static native String getDeviceName(int cellIndex);

	@Override
	protected void finalize() {
		if(cellIndex!=-1)
			if(destroy(cellIndex)<0)
				throw new Error(getError());
	}

	private static native int destroy(int cellIndex);

	public static native int[] getButtonMap(int cursor);

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("WintabAccess:[values=(");
		for(PLevel.Type levelType: PLevel.Type.values()) {
			sb.append(levelType);
			sb.append("=");
			sb.append(getValue(levelType));
			sb.append(",");
		}
		sb.append(") levelRanges=( ");
		for(PLevel.Type levelType: PLevel.Type.values()) {
			sb.append(levelType);
			sb.append("=");
			sb.append(getLevelRange(levelType));
			sb.append(" ");
		}
		sb.append("), cursor=");
		sb.append(getCursor());
		sb.append(", cursorType=");
		sb.append(getCursorType(getCursor()));
		sb.append(", id="+getCursorId(getCursor()));
		sb.append(", buttons=");
		sb.append(getButtons());
		sb.append("]");
		return sb.toString();
	}
}