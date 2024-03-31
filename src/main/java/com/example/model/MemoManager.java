package com.example.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MemoManager {
	// Memoオブジェクトを管理するためのリスト
	private ArrayList<Memo> memoList = new ArrayList<Memo>();

	// Memoオブジェクトを追加する操作
	public void add(Memo memo) {
		memoList.add(memo);
	}
}
