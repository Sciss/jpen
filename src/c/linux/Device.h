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
#ifndef Device_h
#define Device_h
#include "macros.h"
#include <X11/extensions/XInput.h>
#include "Bus.h"

enum{
	E_EventType_ButtonPress,
	E_EventType_ButtonRelease,
	E_EventType_MotionNotify,
	E_EventType_size
};
/* This must be like PLevel.Type enumeration: */
enum {
	E_Valuators_x,
	E_Valuators_y,
	E_Valuators_press,
	//TODO: tiltx
	//TODO: tilty
	E_Valuators_size,
};
struct Device {
	int cellIndex;
	int busCellIndex;
	int index;
	XDevice *pXdevice;

	int valuatorRangeMins[E_Valuators_size];
	int valuatorRangeMaxs[E_Valuators_size];

	XEvent event;
	int valuatorValues[E_Valuators_size];
	int eventTypeIds[E_EventType_size];
	int lastEventType;
	int lastEventButton;
};
m_declareRow(Device);
extern int Device_init(SDevice *pDevice, SBus *pBus, int deviceIndex);
extern int Device_nextEvent(struct Device *pDevice );

#endif
