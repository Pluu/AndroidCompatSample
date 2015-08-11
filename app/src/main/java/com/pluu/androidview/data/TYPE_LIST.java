package com.pluu.androidview.data;

/**
 * Created by PLUUSYSTEM-NEW on 2015-08-11.
 */
public enum TYPE_LIST {
	TYPE1,
	TYPE2,
	TYPE3 {
		@Override
		public boolean isCustom() {
			return true;
		}
	},
	TYPE4 {
		@Override
		public boolean isCustom() {
			return true;
		}
	},
	TYPE5,
	TYPE6 {
		@Override
		public boolean isCustom() {
			return true;
		}
	},
	TYPE7;

	public boolean isCustom() {
		return false;
	}
}
