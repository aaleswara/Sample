// DDX_CBIndex.h : PROJECT_NAME �A�v���P�[�V�����̃��C�� �w�b�_�[ �t�@�C���ł��B
//

#pragma once

#ifndef __AFXWIN_H__
	#error "PCH �ɑ΂��Ă��̃t�@�C�����C���N���[�h����O�� 'stdafx.h' ���C���N���[�h���Ă�������"
#endif

#include "resource.h"		// ���C�� �V���{��


// CDDX_CBIndexApp:
// ���̃N���X�̎����ɂ��ẮADDX_CBIndex.cpp ���Q�Ƃ��Ă��������B
//

class CDDX_CBIndexApp : public CWinApp
{
public:
	CDDX_CBIndexApp();

// �I�[�o�[���C�h
	public:
	virtual BOOL InitInstance();

// ����

	DECLARE_MESSAGE_MAP()
};

extern CDDX_CBIndexApp theApp;