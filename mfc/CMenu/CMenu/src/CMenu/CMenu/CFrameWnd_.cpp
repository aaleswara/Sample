// �w�b�_�t�@�C���̃C���N���[�h
#include "CFrameWnd_.h"	// CFrameWnd_�N���X
#include "resource.h"	// ���\�[�XID

// CFrameWnd_�̃��b�Z�[�W�}�b�v
BEGIN_MESSAGE_MAP(CFrameWnd_, CFrameWnd)
	ON_UPDATE_COMMAND_UI(ID_ITEM_1_1, OnUpdateItem1_1)	// ���j���[���ڂȂǂ�UI�X�V�}�N��.(Item1_1)
	ON_COMMAND(ID_ITEM_1_1, OnItem1_1)	// ���j���[���ڂȂǂ̃R�}���h�}�N��.(Item1_1)
	ON_UPDATE_COMMAND_UI(ID_ITEM_1_2, OnUpdateItem1_2)	// ���j���[���ڂȂǂ�UI�X�V�}�N��.(Item1_2)
	ON_COMMAND(ID_ITEM_1_2, OnItem1_2)	// ���j���[���ڂȂǂ̃R�}���h�}�N��.(Item1_2)
	ON_UPDATE_COMMAND_UI(ID_ITEM_1_3, OnUpdateItem1_3)	// ���j���[���ڂȂǂ�UI�X�V�}�N��.(Item1_3)
	ON_COMMAND(ID_ITEM_1_3, OnItem1_3)	// ���j���[���ڂȂǂ̃R�}���h�}�N��.(Item1_3)
END_MESSAGE_MAP()

// CFrameWnd_�̃����o�̒�`
// �R���X�g���N�^CFrameWnd_()
CFrameWnd_::CFrameWnd_(){	// ����������

}

// �n���h��OnUpdateItem1_1()
void CFrameWnd_::OnUpdateItem1_1(CCmdUI* pCmdUI){	// ���j���[����Item1_1��UI�X�V�n���h��

	// ���j���[����Item1_1��UI�X�V
	if (pCmdUI){	// pCmdUI��NULL�łȂ��Ȃ�.

		// ���j���[����Item1_1�̗L����
		pCmdUI->Enable();	// Enable��Item1_1��L���ɂ���.(�f�t�H���g������TRUE.)

	}

}

// �n���h��OnItem1_1()
void CFrameWnd_::OnItem1_1(){	// ���j���[����Item1_1�̃R�}���h�n���h��

	// ���j���[�̃|�C���^���擾.
	CMenu *pMenu = GetMenu();	// GetMenu�Ń��j���[�̃|�C���^���擾��, pMenu�Ɋi�[.

	// ���j���[�n���h���̎擾.
	HMENU hMenu = pMenu->m_hMenu;	// pMenu�̃��j���[�n���h��m_hMenu��hMenu�Ɋi�[.

	// ���j���[hMenu��0�Ԗڂ̃��j���[���ڂƂȂ��Ă���|�b�v�A�b�v���j���[�̃n���h�����擾.
	HMENU hPopupMenu1 = ::GetSubMenu(hMenu, 0);	// WindowsAPI��GetSubMenu��0�Ԗڂ̃|�b�v�A�b�v���j���[�n���h�����擾��, hPopupMenu1�Ɋi�[.

	// �|�b�v�A�b�v���j���[���ڂ̒ǉ�.
	::AppendMenu(hPopupMenu1, MF_ENABLED | MF_STRING, ID_ITEM_1_4, _T("Item1-4(&M)"));	// WindowsAPI��AppendMenu��hPopupMenu1��"Item1-4"��ǉ�.

}

// �n���h��OnUpdateItem1_2()
void CFrameWnd_::OnUpdateItem1_2(CCmdUI* pCmdUI){	// ���j���[����Item1_2��UI�X�V�n���h��

	// ���j���[����Item1_2��UI�X�V
	if (pCmdUI){	// pCmdUI��NULL�łȂ��Ȃ�.

		// ���j���[����Item1_2�̗L����
		pCmdUI->Enable();	// Enable��Item1_2��L���ɂ���.(�f�t�H���g������TRUE.)

	}

}

// �n���h��OnItem1_2()
void CFrameWnd_::OnItem1_2(){	// ���j���[����Item1_2�̃R�}���h�n���h��

	// ���j���[����Item1_2���I�����ꂽ��.
	AfxMessageBox(_T("Item1-2"), MB_OK | MB_ICONASTERISK);	// AfxMessageBox��"Item1-2"�ƕ\��.

}

// �n���h��OnUpdateItem1_3()
void CFrameWnd_::OnUpdateItem1_3(CCmdUI* pCmdUI){	// ���j���[����Item1_3��UI�X�V�n���h��

	// ���j���[����Item1_3��UI�X�V
	if (pCmdUI){	// pCmdUI��NULL�łȂ��Ȃ�.

		// ���j���[����Item1_3�̗L����
		pCmdUI->Enable();	// Enable��Item1_3��L���ɂ���.(�f�t�H���g������TRUE.)

	}

}

// �n���h��OnItem1_3()
void CFrameWnd_::OnItem1_3(){	// ���j���[����Item1_3�̃R�}���h�n���h��

	// ���j���[����Item1_3���I�����ꂽ��.
	AfxMessageBox(_T("Item1-3"), MB_OK | MB_ICONASTERISK);	// AfxMessageBox��"Item1-3"�ƕ\��.

}