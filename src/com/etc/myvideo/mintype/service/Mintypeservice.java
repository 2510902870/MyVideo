package com.etc.myvideo.mintype.service;

import java.util.List;

import com.etc.myvideo.mintype.dao.Mintypedao;
import com.etc.myvideo.mintype.entity.Mintype;

public class Mintypeservice {
	private Mintypedao mintypedao;

	public void setMintypedao(Mintypedao mintypedao) {
		this.mintypedao = mintypedao;
	}

	// ��ѯ���е�С����
	public List<Mintype> findMintype() {

		return mintypedao.findMintype();
	}

}
