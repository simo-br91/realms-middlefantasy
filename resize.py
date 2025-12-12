from PIL import Image
import os

def process_folder(input_dir, output_dir, size=(32, 32)):
    # Create output directory if it doesn't exist
    os.makedirs(output_dir, exist_ok=True)

    for filename in os.listdir(input_dir):
        # Process only image files
        if not filename.lower().endswith((".png", ".jpg", ".jpeg", ".bmp", ".tga")):
            continue

        input_path = os.path.join(input_dir, filename)
        output_path = os.path.join(output_dir, filename)

        try:
            img = Image.open(input_path)

            # Resize using NEAREST for pixel-art style
            img_resized = img.resize(size, Image.NEAREST)

            # Ensure RGBA (transparency support)
            if img_resized.mode != "RGBA":
                img_resized = img_resized.convert("RGBA")

            # Save as PNG (keeps transparency)
            img_resized.save(output_path, format="PNG")
            print(f"✔ Processed: {filename}")

        except Exception as e:
            print(f"✖ Failed on {filename}: {e}")

# -------- CONFIG --------

input_folder = r"C:/Users/simor/Documents/realms-middlefantasy/input"
output_folder = r"C:/Users/simor/Documents/realms-middlefantasy/processed_textures"

process_folder(input_folder, output_folder)
