// ���O��Ԃ̓o�^
using System;           // ���ʃf�[�^�^�Ɗ�{�N���X(System���O���)
using System.Threading; // �}���`�X���b�h(System.Threading���O���)

// MainClass�̒�`
class MainClass
{
    // Main���\�b�h�̒�`
    static void Main()
    {

        // �X���b�h�̐���.
        Thread thread = new Thread(new ThreadStart(ThreadFunc));    // Thread�I�u�W�F�N�gthread���쐬.(�R���X�g���N�^�ɂ�ThreadStart�f���Q�[�g�̃R���X�g���N�^.����ɂ��̈�����ThreadFunc��n��.)

        // �X���b�h�̊J�n.
        thread.Start(); // thread.Start�ŃX���b�h�J�n.

        // "Main(1)�`Main(5)"�܂ŏo��.
        for (int i = 1; i <= 5; i++)    // i��1����5�܂ŌJ��Ԃ�.
        {

            // "Main"��i���o��.
            Console.WriteLine("Main(" + i + ")"); // "Main(i)"���o��.

        }
        
    }

    // ���������X���b�h�Ŏ��s���郁�\�b�hThreadFunc�̒�`
    static void ThreadFunc(){

        // "ThreadFunc(1)"�`"ThreadFunc(5)"�܂ŏo��.
        for (int i = 1; i <= 5; i++)    // i��1����5�܂ŌJ��Ԃ�.
        {

            // "ThreadFunc"��i���o��.
            Console.WriteLine("ThreadFunc(" + i + ")"); // "ThreadFunc(i)"���o��.

        }

    }

}