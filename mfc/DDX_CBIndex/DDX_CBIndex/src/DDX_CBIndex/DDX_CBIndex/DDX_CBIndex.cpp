// DDX_CBIndex.cpp : アプリケーションのクラス動作を定義します。
//

#include "stdafx.h"
#include "DDX_CBIndex.h"
#include "DDX_CBIndexDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CDDX_CBIndexApp

BEGIN_MESSAGE_MAP(CDDX_CBIndexApp, CWinApp)
	ON_COMMAND(ID_HELP, &CWinApp::OnHelp)
END_MESSAGE_MAP()


// CDDX_CBIndexApp コンストラクション

CDDX_CBIndexApp::CDDX_CBIndexApp()
{
	// TODO: この位置に構築用コードを追加してください。
	// ここに InitInstance 中の重要な初期化処理をすべて記述してください。
}


// 唯一の CDDX_CBIndexApp オブジェクトです。

CDDX_CBIndexApp theApp;


// CDDX_CBIndexApp 初期化

BOOL CDDX_CBIndexApp::InitInstance()
{
	CWinApp::InitInstance();

	// 標準初期化
	// これらの機能を使わずに最終的な実行可能ファイルの
	// サイズを縮小したい場合は、以下から不要な初期化
	// ルーチンを削除してください。
	// 設定が格納されているレジストリ キーを変更します。
	// TODO: 会社名または組織名などの適切な文字列に
	// この文字列を変更してください。
	SetRegistryKey(_T("アプリケーション ウィザードで生成されたローカル アプリケーション"));

	CDDX_CBIndexDlg dlg;
	m_pMainWnd = &dlg;
	INT_PTR nResponse = dlg.DoModal();
	if (nResponse == IDOK)
	{
		// TODO: ダイアログが <OK> で消された時のコードを
		//  記述してください。
	}
	else if (nResponse == IDCANCEL)
	{
		// TODO: ダイアログが <キャンセル> で消された時のコードを
		//  記述してください。
	}

	// ダイアログは閉じられました。アプリケーションのメッセージ ポンプを開始しないで
	//  アプリケーションを終了するために FALSE を返してください。
	return FALSE;
}
