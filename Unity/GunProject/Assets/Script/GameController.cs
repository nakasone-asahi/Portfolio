using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    // Start is called before the first frame update

    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

      //エンターキーが入力された場合「true」
      if (Input.GetKeyDown(KeyCode.Return)){
        SceneManager.LoadScene("MainScene");
      }
    }
}
