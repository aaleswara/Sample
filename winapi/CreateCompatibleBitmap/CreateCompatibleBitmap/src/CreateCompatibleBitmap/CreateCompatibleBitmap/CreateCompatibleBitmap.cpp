// ヘッダファイルのインクルード
#include <windows.h>	// 標準WindowsAPI
#include <tchar.h>		// TCHAR型

// 関数のプロトタイプ宣言
LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam);	// ウィンドウメッセージに対して独自の処理をするように定義したコールバンク関数WindowProc.
 
// _tWinMain関数の定義
int WINAPI _tWinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPTSTR lpCmdLine, int nShowCmd){
 
	// 変数の宣言
	HWND hWnd;			// CreateWindowで作成したウィンドウのウィンドウハンドルを格納するHWND型変数hWnd.
	MSG msg;			// ウィンドウメッセージ情報を格納するMSG構造体型変数msg.
	WNDCLASS wc;		// ウィンドウクラス情報をもつWNDCLASS構造体型変数wc.
 
	// ウィンドウクラスの設定
	wc.lpszClassName = _T("CreateCompatibleBitmap");		// ウィンドウクラス名は"CreateCompatibleBitmap".
	wc.style = CS_HREDRAW | CS_VREDRAW;						// スタイルはCS_HREDRAW | CS_VREDRAW.
	wc.lpfnWndProc = WindowProc;							// ウィンドウプロシージャは独自の処理を定義したWindowProc.
	wc.hInstance = hInstance;								// インスタンスハンドルは_tWinMainの引数.
	wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);				// アイコンはアプリケーション既定のもの.
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);				// カーソルは矢印.
	wc.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);	// 背景は白ブラシ.
	wc.lpszMenuName = NULL;									// メニューはなし.
	wc.cbClsExtra = 0;										// 0でいい.
	wc.cbWndExtra = 0;										// 0でいい.
 
	// ウィンドウクラスの登録
	if (!RegisterClass(&wc)){	// RegisterClassでウィンドウクラスを登録し, 0が返ったらエラー.
 
		// エラー処理
		MessageBox(NULL, _T("RegisterClass failed!"), _T("CreateCompatibleBitmap"), MB_OK | MB_ICONHAND);	// MessageBoxで"RegisterClass failed!"とエラーメッセージを表示.
		return -1;	// 異常終了(1)
 
	}
 
	// ウィンドウの作成
	hWnd = CreateWindow(_T("CreateCompatibleBitmap"), _T("CreateCompatibleBitmap"), WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, NULL, NULL, hInstance, NULL);	// CreateWindowで, 上で登録した"CreateCompatibleBitmap"ウィンドウクラスのウィンドウを作成.
	if (hWnd == NULL){	// ウィンドウの作成に失敗したとき.
 
		// エラー処理
		MessageBox(NULL, _T("CreateWindow failed!"), _T("CreateCompatibleBitmap"), MB_OK | MB_ICONHAND);	// MessageBoxで"CreateWindow failed!"とエラーメッセージを表示.
		return -2;	// 異常終了(2)
 
	}
 
	// ウィンドウの表示
	ShowWindow(hWnd, SW_SHOW);	// ShowWindowでSW_SHOWを指定してウィンドウの表示.
 
	// メッセージループ
	while (GetMessage(&msg, NULL, 0, 0) > 0){	// GetMessageでメッセージを取得, 戻り値が0より大きい間はループし続ける.
 
		// ウィンドウメッセージの送出
		DispatchMessage(&msg);	// DispatchMessageで受け取ったメッセージをウィンドウプロシージャ(この場合は独自に定義したWindowProc)に送出.
		TranslateMessage(&msg);	// TranslateMessageで仮想キーメッセージを文字メッセージへ変換.

	}
 
	// プログラムの終了
	return (int)msg.wParam;	// 終了コード(msg.wParam)を戻り値として返す.
 
}
 
// WindowProc関数の定義
LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam){	// ウィンドウメッセージに対して独自の処理をするように定義したウィンドウプロシージャ.

	// ウィンドウプロシージャ全体で使われるスタティック変数の宣言.
	static HBITMAP hLoadBitmap;	// ロードしたビットマップのハンドルを格納するHBITMAP型スタティック変数hLoadBitmap.
	static HBITMAP hBackBitmap;	// バックバッファ用ビットマップのハンドルを格納するHBITMAP型スタティック変数hBackBitmap.
	static int iMode;			// 通常モード: 0, ダブルバッファリングモード: 1.
	static int x;				// 矩形を描画する位置のx座標.
	static int y;				// 矩形を描画する位置のy座標.

	// ウィンドウメッセージに対する処理.
	switch (uMsg){	// switch-casa文でuMsgの値ごとに処理を振り分ける.
 
		// ウィンドウの作成が開始された時.
		case WM_CREATE:		// ウィンドウの作成が開始された時.(uMsgがWM_CREATEの時.)
 
			// WM_CREATEブロック
			{

				// このブロックのローカル変数の宣言・初期化
				LPCREATESTRUCT lpcs;	// lParamから渡されたCREATESTRUCTへのポインタを格納するlpcs.
				int iRet;				// MessageBoxの戻り値を格納するint型変数iRet.
				HDC hDC = NULL;			// ウィンドウのデバイスコンテキストハンドルhDCをNULLに初期化.(互換ビットマップ作成に必要.)

				// lParamをlpcsに渡す.
				lpcs = (LPCREATESTRUCT)lParam; // lParamをLPCREATESTRUCTにキャストしてlpcsに格納.

				// 通常モードかダブルバッファリングモードかを選択.
				iRet = MessageBox(hwnd, _T("Use DoubleBufferingMode?"), _T("CreateCompatibleBitmap"), MB_YESNO | MB_ICONQUESTION);	// MessageBoxでダブルバッファリングモードを使うかどうかのメッセージボックスを表示.
				if (iRet == IDYES){	// "はい"を選択した場合.

					// 描画モードをダブルバッファリングモードにセット.
					iMode = 1;	// iModeをダブルバッファリングモード( = 1)にする.

					// ウィンドウのデバイスコンテキストハンドルを取得.
					hDC = GetDC(hwnd);	// GetDCでウィンドウのデバイスコンテキストハンドルを取得し, hDCに格納.

					// バックバッファ用ビットマップを生成.
					hBackBitmap = (HBITMAP)CreateCompatibleBitmap(hDC, 640, 480);	// CreateCompatibleBitmapでサイズ(640, 480)のhDCと互換性のあるビットマップhBackBitmapを作成.

					// ウィンドウのデバイスコンテキストを解放.
					ReleaseDC(hwnd, hDC);	// ReleaseDCでhDCを解放.
					hDC = NULL;	// hDCをNULLにする.

				}

				// ビットマップのロード
				hLoadBitmap = (HBITMAP)LoadImage(lpcs->hInstance, _T("test.bmp"), IMAGE_BITMAP, 0, 0, LR_LOADFROMFILE);	// LoadImageで"test.bmp"をロードし, 戻り値のビットマップハンドルはhLoadBitmapに格納する.
				if (hLoadBitmap == NULL){	// hLoadBitmapがNULLならロード失敗.

					// エラー処理
					MessageBox(hwnd, _T("LoadImage failed!"), _T("CreateCompatibleBitmap"), MB_OK | MB_ICONHAND);	// MessageBoxで"LoadImage failed!"とエラーメッセージを表示.
					if (iMode == 1){	// ダブルバッファリングモードの場合.
						if (hBackBitmap != NULL){	// バックバッファ用ビットマップが破棄されていない場合.
							DeleteObject(hBackBitmap);	// DeleteObjectでhBackBitmapを破棄.
							hBackBitmap = NULL;	// hBackBitmapをNULLにしておく.
						}
					}
					return -1;	// 異常終了なので-1を返して, ウィンドウ生成失敗とする.

				}

				// ウィンドウ作成成功
				return 0;	// return文で0を返して, ウィンドウ作成成功とする.
 
			}
 
			// 既定の処理へ向かう.
			break;	// breakで抜けて, 既定の処理(DefWindowProc)へ向かう.
 
		// ウィンドウが破棄された時.
		case WM_DESTROY:	// ウィンドウが破棄された時.(uMsgがWM_DESTROYの時.)
 
			// WM_DESTROYブロック
			{

				// ダブルバッファリングモードならバックバッファの終了処理.
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					if (hBackBitmap != NULL){	// バックバッファ用ビットマップが破棄されていない場合.
						DeleteObject(hBackBitmap);	// DeleteObjectでhBackBitmapを破棄.
						hBackBitmap = NULL;	// hBackBitmapをNULLにしておく.
					}
				}

				// ロードしたビットマップの終了処理
				if (hLoadBitmap != NULL){	// hLoadBitmapがNULLでない場合.(ロードしたままの状態の場合.)

					// ロードしたビットマップの破棄
					DeleteObject(hLoadBitmap);	// DeleteObjectでhLoadBitmapの破棄.
					hLoadBitmap = NULL;	// hLoadBitmapはNULLにしておく.

				}

				// 終了メッセージの送信.
				PostQuitMessage(0);	// PostQuitMessageで終了コードを0としてWM_QUITメッセージを送信.(するとメッセージループのGetMessageの戻り値が0になるので, メッセージループから抜ける.)
 
			}
 
			// 既定の処理へ向かう.
			break;	// breakで抜けて, 既定の処理(DefWindowProc)へ向かう.

		// 画面の描画を要求された時.
		case WM_PAINT:		// 画面の描画を要求された時.(uMsgがWM_PAINTの時.)
 
			// WM_PAINTブロック
			{

				// このブロックのローカル変数の宣言
				HDC hDC;				// デバイスコンテキストハンドルを格納するHDC型変数hDC.
				PAINTSTRUCT ps;			// ペイント情報を管理するPAINTSTRUCT構造体型の変数ps.
				HDC	hLoadMemDC;			// ウィンドウデバイスコンテキストと互換性のあるロードしたビットマップ用メモリデバイスコンテキストハンドルを格納するHDC型変数hLoadMemDC.
				HDC hBackMemDC;			// ウィンドウデバイスコンテキストと互換性のあるバックバッファ用メモリデバイスコンテキストハンドルを格納するHDC型変数hBackMemDC.
				HBITMAP hOldLoadBitmap;	// hLoadMemDCがSelectObjectされるまで選択していた古いビットマップのハンドルhOldLoadBitmap.
				HBITMAP	hOldBackBitmap;	// hBackMemDCがSelectObjectされるまで選択していた古いビットマップのハンドルhOldBackBitmap.

				// ウィンドウの描画開始
				hDC = BeginPaint(hwnd, &ps);	// BeginPaintでこのウィンドウの描画の準備をする. 戻り値にはデバイスコンテキストハンドルが返るので, hDCに格納.
				
				// メモリデバイスコンテキストの生成
				hLoadMemDC = CreateCompatibleDC(hDC);	// CreateCompatibleDCでウィンドウのデバイスコンテキストhDCと互換性のあるメモリデバイスコンテキストhLoadMemDCを生成.
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					hBackMemDC = CreateCompatibleDC(hDC);	// CreateCompatibleDCでウィンドウのデバイスコンテキストhDCと互換性のあるメモリデバイスコンテキストhBackMemDCを生成.
				}

				// デバイスコンテキストの選択.
				hOldLoadBitmap = (HBITMAP)SelectObject(hLoadMemDC, hLoadBitmap);	// SelectObjectでhLoadMemDCはhLoadBitmapを選択.(ロード用メモリデバイスコンテキストはロード用ビットマップ.)
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					hOldBackBitmap = (HBITMAP)SelectObject(hBackMemDC, hBackBitmap);	// SelectObjectでhBackMemDCはhBackBitmapを選択.(バックバッファ用メモリデバイスコンテキストはバックバッファ用ビットマップ.)
				}

				// BitBltとRectangleによるダブルバッファリング.
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					BitBlt(hBackMemDC, 0, 0, 640, 480, hLoadMemDC, 0, 0, SRCCOPY);	// BitBltでhLoadMemDCをhBackMemDCに転送.(1枚目は背景として"test.bmp"のピクセル列をバックバッファに転送.)
					Rectangle(hBackMemDC, x, y, x + 100, y + 100);	// Rectangleで矩形を(x, y)にhBackMemDCに描画.(2枚目は動くオブジェクトとして矩形を描画.)
					BitBlt(hDC, 0, 0, 640, 480, hBackMemDC, 0, 0, SRCCOPY);	// hDCにhBackMemDCの内容をコピー.
				}
				else{	// 通常描画.
					BitBlt(hDC, 0, 0, 640, 480, hLoadMemDC, 0, 0, SRCCOPY);	// BitBltでhLoadMemDCをhDCに転送.
					Rectangle(hDC, x, y, x + 100, y + 100);	// Rectangleで矩形を(x, y)にhDCに描画.
				}

				// ビットマップの状態を戻す.
				SelectObject(hLoadMemDC, hOldLoadBitmap);	// hLoadMemDCに元のhOldLoadBitmapに戻すように選択させる.
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					SelectObject(hBackMemDC, hOldBackBitmap);	// hBackMemDCに元のhOldBackBitmapに戻すように選択させる.
				}

				// メモリデバイスコンテキストの破棄
				DeleteDC(hLoadMemDC);	// DeleteDCでhLoadMemDCを破棄.
				if (iMode == 1){	// ダブルバッファリングモードの場合.
					DeleteDC(hBackMemDC);	// DeleteDCでhBackMemDCを破棄.
				}

				// ウィンドウの描画終了
				EndPaint(hwnd, &ps);	// EndPaintでこのウィンドウの描画処理を終了する.

			}

			// 既定の処理へ向かう.
			break;	// breakで抜けて, 既定の処理(DefWindowProc)へ向かう.

		// キーボードの任意のキーが押された時.
		case WM_KEYDOWN:	// キーボードの任意のキーが押された時.(uMsgがWM_KEYDOWNの時.)

			// WM_KEYDOWNブロック
			{

				// 矩形のx座標とy座標を1増やす.
				x += 1;	// xを1増やす.
				y += 1;	// yを1増やす.
				InvalidateRect(hwnd, NULL, FALSE);	// 画面の更新を要求.

			}

			// 既定の処理へ向かう.
			break;	// breakで抜けて, 既定の処理(DefWindowProc)へ向かう.

		// 上記以外の時.
		default:	// 上記以外の値の時の既定処理.
 
			// 既定の処理へ向かう.
			break;	// breakで抜けて, 既定の処理(DefWindowProc)へ向かう.
 
	}
 
	// あとは既定の処理に任せる.
	return DefWindowProc(hwnd, uMsg, wParam, lParam);	// 戻り値も含めDefWindowProcに既定の処理を任せる.

}